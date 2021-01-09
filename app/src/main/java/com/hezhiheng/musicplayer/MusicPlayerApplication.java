package com.hezhiheng.musicplayer;

import android.app.Application;
import android.util.Log;

import io.reactivex.plugins.RxJavaPlugins;

public class MusicPlayerApplication extends Application {
    private static MusicPlayerApplication instance;

    public static MusicPlayerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setRxJavaErrorHandler();
    }

    private void setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            throwable.printStackTrace();
            Log.e("MyApplication", "MyApplication setRxJavaErrorHandler "  + throwable.getMessage());
        });
    }
}
