package com.softxpert.cars;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    public static Context contextApp;

    @Override
    public void onCreate() {
        super.onCreate();
        contextApp = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //   MultiDex.install(this);
    }
}
