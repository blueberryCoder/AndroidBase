package com.blueberry.sample.module.http;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by blueberry on 2016/8/16.
 */
public final class RequestQueue {

    private static final String TAG = "RequestQueue";

    //线程安全的请求队列
    private BlockingQueue<Request<?>> mRequestQueue = new PriorityBlockingQueue<>();
    //请求的序列化生成器
    private AtomicInteger mSerialNumGenerator = new AtomicInteger(0);
    //默认的核心数 为CPU 个数+1
    public static int DEFAULT_CORE_NUMS = Runtime.getRuntime().availableProcessors()+1;
    // cpu核心数+1分发线程
    private int mDispatchNums = DEFAULT_CORE_NUMS;
    //NetworkExecutor[],执行网络请求的线程
    private NetworkExecutor[] mDispatchers =null;
    // Http 请求的真正执行者
    private HttpStack mHttpStack;

    protected RequestQueue(int coreNums,HttpStack httpStack){
        mDispatchNums = coreNums;
        mHttpStack = httpStack!=null?httpStack:HttpStackFactory.createHttpStack();
    }

    // 启动NetworkExecutor
    private final void startNetworkExecutors(){
        mDispatchers =  new NetworkExecutor[mDispatchNums];
        for (int i = 0; i < mDispatchNums; i++) {
            mDispatchers[i] = new NetworkExecutor(mRequestQueue,mHttpStack);
            mDispatchers[i].start();
        }
    }

    public void start(){
        stop();
        startNetworkExecutors();
    }


    /**
     * 停止NetworkExecutor
     */
    private void stop() {
        if(mDispatchers!=null && mDispatchers.length>0){
            for (int i = 0; i < mDispatchers.length; i++) {
                mDispatchers[i].quit();
            }
        }
    }

    public void addRequest(Request<?> request){
        if(!mRequestQueue.contains(request)){
            //为请求设置序列号
            request.setSerialNumber(this.generateSerialNumber());
            mRequestQueue.add(request);
        }else{
            Log.d(TAG,"请求队列中已经含有了")  ;
        }
    }

    private int generateSerialNumber() {
        return mSerialNumGenerator.incrementAndGet();
    }


}
