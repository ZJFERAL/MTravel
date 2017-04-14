package com.zjf.core.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author :ZJF
 * @version : 2016-12-26 上午 10:12
 */

public class RetrofitUtils {

    private static String mBaseUrl;

    public static void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    private static OkHttpClient.Builder builder;

    static {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
    }

    public static Retrofit getClient(String baseUrl, OkHttpClient client) {
        OkHttpClient localClient = builder.build();
        if (client != null) {
            localClient = client;
        }
        return new Retrofit.Builder()
                .client(localClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Retrofit getClient(OkHttpClient client) {
        OkHttpClient localClient = builder.build();
        if (client != null) {
            localClient = client;
        }
        return new Retrofit.Builder()
                .client(localClient)
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
