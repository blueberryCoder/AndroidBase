package com.blueberry.sample.module.http;

import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by blueberry on 2016/8/16.
 */
public class Response extends BasicHttpResponse {
    //原始的response 主体数据
    public byte[] rawData = new byte[0];

    public Response(StatusLine statusline, ReasonPhraseCatalog catalog, Locale locale) {
        super(statusline, catalog, locale);
    }

    public Response(StatusLine statusline) {
        super(statusline);
    }

    public Response(ProtocolVersion ver, int code, String reason) {
        super(ver, code, reason);
    }

    public void setEntity(HttpEntity entity){
        super.setEntity(entity);
        rawData = entityToBytes(getEntity());
    }

    private byte[] entityToBytes(HttpEntity entity) {
        try {
            return EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public byte[] getRawData() {
        return rawData;
    }

    public String getMessage() {
        return getReason(getStatusLine().getStatusCode());
    }

    public int getStatusCode() {
        return getStatusLine().getStatusCode();
    }
}
