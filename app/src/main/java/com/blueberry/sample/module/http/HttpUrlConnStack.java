package com.blueberry.sample.module.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicStatusLine;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by blueberry on 2016/8/16.
 */
public class HttpUrlConnStack implements HttpStack {

    private Config mConfig = new Config.Builder().build();

    @Override
    public Response performRequest(Request<?> request) {
        HttpURLConnection urlConnection = null;
        //构建HttpURLConnection
        try {
            urlConnection = createUrlConnection(request.getUrl());
            //设置headers
            setRequestHeaders(urlConnection, request);
            //设置Body参数
            setRequestParams(urlConnection, request);
            return fetchResponse(urlConnection);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response fetchResponse(HttpURLConnection urlConnection) throws IOException {
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        int responseCode = urlConnection.getResponseCode();
        if (responseCode == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        // 状态行数据
        StatusLine responseStatus = new BasicStatusLine(protocolVersion, responseCode, urlConnection
                .getResponseMessage());
        // 构建response.
        Response response = new Response(responseStatus);
        //设置 response数据
        response.setEntity(entityFromURLConnection(urlConnection));
        addHeaderToResponse(response, urlConnection);
        return response;
    }

    private void addHeaderToResponse(Response response, HttpURLConnection urlConnection) {
        for (Map.Entry<String, List<String>> header : urlConnection.getHeaderFields().entrySet()) {
            if (header.getKey() != null) {
                Header h = new BasicHeader(header.getKey(), header.getValue().get(0));
                response.addHeader(h);
            }
        }
    }

    private HttpEntity entityFromURLConnection(HttpURLConnection urlConnection) {
        BasicHttpEntity entity = new BasicHttpEntity();
        InputStream inputStream = null;
        try {
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            inputStream = urlConnection.getErrorStream();
        }
        entity.setContent(inputStream);
        entity.setContentLength(urlConnection.getContentLength());
        entity.setContentEncoding(urlConnection.getContentEncoding());
        entity.setContentType(urlConnection.getContentType());
        return entity;
    }

    private void setRequestParams(HttpURLConnection urlConnection, Request<?> request)
            throws IOException {
        Request.HttpMethod method = request.getHttpMethod();
        urlConnection.setRequestMethod(method.toString());
        // add params
        byte[] body = request.getBody();
        if (body != null) {
            urlConnection.setDoOutput(true);
            // set content type
            urlConnection.addRequestProperty(Request.HEADER_CONTENT_TYPE,
                    request.getBodyContentType());
            // write params data to connection.
            DataOutputStream dataOutputStream =
                    new DataOutputStream(urlConnection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }

    private void setRequestHeaders(HttpURLConnection urlConnection, Request<?> request) {
        Set<String> headersKeys = request.getHeaders().keySet();
        for (String headerName : headersKeys) {
            urlConnection.addRequestProperty(headerName, request.getHeaders().get(headerName));
        }
    }

    private HttpURLConnection createUrlConnection(String url) throws IOException {
        URL newURL = new URL(url);
        URLConnection urlConnection = newURL.openConnection();
        urlConnection.setConnectTimeout(mConfig.connTimeOut);
        urlConnection.setReadTimeout(mConfig.soTimeOut);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(true);
        return (HttpURLConnection) urlConnection;
    }
}
