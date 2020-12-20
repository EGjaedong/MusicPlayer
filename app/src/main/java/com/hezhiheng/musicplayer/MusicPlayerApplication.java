package com.hezhiheng.musicplayer;

import android.app.Application;

public class MusicPlayerApplication extends Application {
    private static MusicPlayerApplication instance;

    public static MusicPlayerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
