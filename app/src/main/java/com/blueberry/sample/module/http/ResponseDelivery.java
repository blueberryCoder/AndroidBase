package com.blueberry.sample.module.http;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by blueberry on 2016/8/16.
 * 请求结果投递类，将请求结果投递给UI线程
 */
public class ResponseDelivery implements Executor{
    /*关联主线程消息队列的handler*/
    Handler mResponseHandler = new Handler(Looper.getMainLooper());

    public void deliveryResponse(final Request<?> request, final Response response) {
        Runnable respRunnable = new Runnable() {
            @Override
            public void run() {
                request.deliveryResponse(response);
            }
        };
        execute(respRunnable);
    }

    @Override
    public void execute(Runnable command) {
        mResponseHandler.post(command);
    }
}
