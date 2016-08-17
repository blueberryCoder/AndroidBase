package com.blueberry.sample.module.http;

/**
 * Created by blueberry on 2016/8/16.
 */
public class NetManager {

    static RequestQueue sRequestQueue = new RequestQueue(5, null);

    public static RequestQueue newRequestQueue() {
        return sRequestQueue;
    }
}
