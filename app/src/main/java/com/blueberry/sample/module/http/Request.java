package com.blueberry.sample.module.http;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by blueberry on 2016/8/16.
 * 网络请求类,注意GET和DELETE不能传递请求参数，因为其请求的性质所致，用户可以将参数构建到URL后传进到Request
 * 中。
 */
public abstract class Request<T> implements Comparable<Request<T>> {

    //默认的编码格式
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    //请求序列红啊
    protected int mSerialNum = 0;
    //优先级默认为NORMAL
    protected Priority mPriority = Priority.NORMAL;
    //是否取消该请求
    protected boolean isCancel = false;
    //请求是否应该缓存
    private boolean mShouldCache = true;
    //请求Listener
    protected RequestListener<T> mRequestListener;
    //请求的URL
    private String mUrl = "";
    //请求的方法
    HttpMethod mHttpMethod = HttpMethod.GET;
    //请求的header
    private Map<String, String> mHeader = new HashMap<>();
    //请求参数
    private Map<String, String> mBodyParams = new HashMap<>();

    public Request(HttpMethod httpMethod, String url, RequestListener<T> listener) {
        mHttpMethod = httpMethod;
        mUrl = url;
        mRequestListener = listener;
    }

    //从原生的网络请求中解析结果，子类必须覆写
    public abstract T parseResponse(Response response);

    //处理Response ,该方法需要运行在UI线程
    public final void deliveryResponse(Response response) {
        //解析得到请求结果
        T result = parseResponse(response);
        if (mRequestListener != null) {
            int stCode = response != null ? response.getStatusCode() : -1;
            String msg = response != null ? response.getMessage() : "unknown error";
            mRequestListener.onComplete(stCode, result, msg);
        }
    }

    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded;charset=" + getParamsEncoding();
    }

    //返回POST或者PUT请求时的Body参数字节数组
    public byte[] getBody() {
        Map<String, String> params = getParams();
        if (params != null && params.size() > 0) {
            return encodeParameters(params, getParamsEncoding());
        }
        return null;
    }

    //将参数转换为 URL编码的参数串,格式key1=value&key2=value2
    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodeParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodeParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodeParams.append('=');
                encodeParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodeParams.append('&');
            }
            return encodeParams.toString().getBytes(paramsEncoding);
        } catch (Exception e) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, e);
        }

    }

    //用于对象的排序处理,根据优先级加入到队列的序号进行排序
    @Override
    public int compareTo(Request<T> another) {
        Priority myPriority = this.getPriority();
        Priority anotherPriority = another.getPriority();
        return myPriority.equals(anotherPriority)
                ? this.getSerialNumber() - another.getSerialNumber()
                : myPriority.ordinal() - anotherPriority.ordinal();
    }

    public Map<String, String> getParams() {
        return mBodyParams;
    }

    public Priority getPriority() {
        return mPriority;
    }

    public int getSerialNumber() {
        return mSerialNum;
    }

    public void setSerialNumber(int serialNumber) {
        this.mSerialNum = serialNumber;
    }

    public void setShouldCache(boolean shouldCache) {
        this.mShouldCache = shouldCache;
    }

    public String getUrl() {
        return mUrl;
    }

    public  boolean shouldCache(){
        return mShouldCache;
    }

    public Map<String, String> getHeaders() {
        return mHeader;
    }

    public HttpMethod getHttpMethod() {
        return mHttpMethod;
    }

    public static enum HttpMethod {
        GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");
        private String mHttpMethod = "";

        private HttpMethod(String method) {
            this.mHttpMethod = method;
        }

        @Override
        public String toString() {
            return mHttpMethod;
        }
    }

    public static enum Priority {
        LOW, NORMAL, HIGH, IMMEDIATE
    }

    public static interface RequestListener<T> {
        //请求完成回调
        public void onComplete(int stCode, T response, String errMsg);
    }
}
