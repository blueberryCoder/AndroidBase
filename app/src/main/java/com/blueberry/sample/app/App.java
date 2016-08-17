package com.blueberry.sample.app;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by blueberry on 2016/5/5.
 * <p/>
 * app
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("Shares").setLogLevel(LogLevel.FULL);
    }
}
