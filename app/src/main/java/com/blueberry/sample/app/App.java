package com.blueberry.sample.app;

import android.app.Application;
import android.databinding.DataBindingUtil;
import android.os.StrictMode;

import com.activeandroid.ActiveAndroid;
import com.blueberry.sample.module.data_binding.component.TestComponent;
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
        DataBindingUtil.setDefaultComponent(new TestComponent());

        Logger.init("Shares").setLogLevel(LogLevel.FULL);
        ActiveAndroid.initialize(this);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().build());
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().build());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();

    }


}
