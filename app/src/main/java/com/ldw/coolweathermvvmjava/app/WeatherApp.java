package com.ldw.coolweathermvvmjava.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


public class WeatherApp extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
