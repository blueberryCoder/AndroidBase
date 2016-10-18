package com.blueberry.sample.utils;

import android.os.Looper;

/**
 * Created by blueberry on 2016/8/8.
 */
public class Async {
    private Async(){}

    public static void runOnMainThread( Runnable run){
        new android.os.Handler(Looper.getMainLooper()).postDelayed(run,0);
    }

    public static void runOnMainThread(Runnable run, long delayed){
        new android.os.Handler(Looper.getMainLooper()).postDelayed(run,delayed);
    }
}
