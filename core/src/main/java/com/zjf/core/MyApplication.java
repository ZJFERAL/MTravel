package com.zjf.core;

import android.app.Application;

/**
 * Created by machengyuan on 2017/4/10.
 */

public class MyApplication extends Application{
    protected static MyApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static String getStringRes(int id) {
        return instance.getString(id);
    }

}
