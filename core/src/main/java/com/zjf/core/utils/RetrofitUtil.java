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

public class RetrofitUtil {

    private static String mBaseUrl;

    public static void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    private static OkHttpClient.Builder builder;

    static {
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(3, TimeUnit.SECONDS);
    }

    public static Retrofit getClient(String baseUrl) {

        return new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Retrofit getClient() {

        return new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
