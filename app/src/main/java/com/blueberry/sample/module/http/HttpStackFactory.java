package com.blueberry.sample.module.http;

import android.os.Build;

/**
 * Created by blueberry on 2016/8/16.
 */
public class HttpStackFactory {
    private static final int GINGERBREAD_SDK_NUM =9;
    public static HttpStack createHttpStack() {
        int runtimeSDKApi = Build.VERSION.SDK_INT ;
        if(runtimeSDKApi>=GINGERBREAD_SDK_NUM){
            return new HttpUrlConnStack();
        }
        return new HttpClientStack();
    }


}
