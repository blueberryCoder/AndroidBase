package com.blueberry.sample.module.http;

/**
 * Created by blueberry on 2016/8/16.
 */
public interface HttpStack {
    Response performRequest(Request<?> request);
}
