package com.blueberry.sample.module.http;

/**
 * Created by blueberry on 2016/8/16.
 */
public class StringRequest extends Request<String> {
    public StringRequest(HttpMethod httpMethod, String url, RequestListener<String> listener) {
        super(httpMethod, url, listener);
    }

    @Override
    public String parseResponse(Response response) {
        return new String(response.getRawData());
    }
}
