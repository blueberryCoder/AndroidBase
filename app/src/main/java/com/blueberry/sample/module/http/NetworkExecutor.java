package com.blueberry.sample.module.http;

import android.util.Log;

import java.util.concurrent.BlockingQueue;

/**
 * Created by blueberry on 2016/8/16.
 */
public class NetworkExecutor extends Thread {

    private static final String TAG = "NetworkExecutor";
    //网络请求队列
    private BlockingQueue<Request<?>> mRequestQueue;
    //网络请求栈
    private HttpStack mHttpStack;
    //结果分发器,将结果投递到主线程
    private static ResponseDelivery mResponseDelivery = new ResponseDelivery();
    //请求缓存
    private static Cache<String, Response> mReqCache = new Cache.LruMemCache<String, Response>();

    //是否停止
    private boolean isStop = false;

    public NetworkExecutor(BlockingQueue<Request<?>> mRequestQueue, HttpStack mHttpStack) {
        this.mRequestQueue = mRequestQueue;
        this.mHttpStack = mHttpStack;
    }

    @Override
    public void run() {
        try {
            while (!isStop) {
                final Request<?> request = mRequestQueue.take();
                if (request.isCancel) {
                    continue;
                }

                Response response = null;
                if (isUseCache(request)) {
                    //从缓存中读取
                    response = mReqCache.get(request.getUrl());
                } else {
                    // 从网络上获取数据
                    response = mHttpStack.performRequest(request);
                    //如果该请求需要缓存，那么请求成功缓存到mResponseCache中
                    if (request.shouldCache() && isSuccess(response)) {
                        mReqCache.put(request.getUrl(), response);
                    }
                }
                mResponseDelivery.deliveryResponse(request, response);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isSuccess(Response response) {
        return response != null && response.getStatusCode() == 200;
    }

    private boolean isUseCache(Request<?> request) {
        return request.shouldCache() && mReqCache.get(request.getUrl()) != null;
    }

    public void quit() {
        isStop =true;
    }
}
