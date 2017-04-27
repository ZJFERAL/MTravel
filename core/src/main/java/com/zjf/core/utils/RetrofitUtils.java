package com.zjf.core.utils;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    public static Retrofit getClient(String baseUrl, OkHttpClient client, Context context) {
        mBaseUrl = baseUrl;
        builder.addInterceptor(getCacheInterceptor(context))
                .addNetworkInterceptor(getCacheInterceptor(context))
                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024));
        return getClient(client);
    }

    public static Retrofit getClient(String baseUrl, OkHttpClient client) {
        mBaseUrl = baseUrl;
        return getClient(client);
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

    public static Interceptor getCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtils.isNetworkReachable(context)) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);
                if (NetUtils.isNetworkReachable(context)) {//有网络情况下，根据请求接口的设置，配置缓存。
                    if (NetUtils.getWifiEnabled(context)) {
                        //这样在下次请求时，根据缓存决定是否真正发出请求。
                        String cacheControl = request.cacheControl().toString();
                        //当然如果你想在有网络的情况下都直接走网络，那么只需要
                        //将其超时时间这是为0即可:String cacheControl="Cache-Control:public,max-age=0"
                        return response.newBuilder().header("Cache-Control", cacheControl)
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        return response.newBuilder().header("Cache-Control", "public,only-if-cached,max-stale=" + 60 * 5)
                                .removeHeader("Pragma")
                                .build();
                    }
                } else {//无网络
                    return response.newBuilder().header("Cache-Control", "public,only-if-cached,max-stale=360000")
                            .removeHeader("Pragma")
                            .build();
                }

            }
        };
    }
}
