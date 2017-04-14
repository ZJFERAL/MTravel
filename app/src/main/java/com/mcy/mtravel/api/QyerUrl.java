package com.mcy.mtravel.api;

import com.mcy.mtravel.entity.IndexBean;
import com.mcy.mtravel.entity.TokenBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by zhaojifeng on 2017/4/12.
 */

public interface QyerUrl {


    @FormUrlEncoded
    @POST(value = "device/register")
    Observable<TokenBean> getInitToken(@Header("QYER-Token") String token,
                                       @Header("QYER-Authorization") String auth,
                                       @Header("Content-Type") String cType,
                                       @Field("app_id") String appId,
                                       @Field("app_version") String appVersion,
                                       @Field("device_id") String deviceID,
                                       @Field("init") int init);

    @FormUrlEncoded
    @POST(value = "device/register")
    Observable<TokenBean> getToken(@Header("QYER-Token") String token,
                                   @Header("QYER-Authorization") String auth,
                                   @Header("Content-Type") String cType,
                                   @Field("app_id") String appId,
                                   @Field("app_version") String appVersion,
                                   @Field("device_id") String deviceID);


    @FormUrlEncoded
    @POST
    Observable<IndexBean> getIndex(@Url String url,
                                   @Header("QYER-Token") String token,
                                   @Header("QYER-Authorization") String auth,
                                   @Header("Content-Type") String cType,
                                   @Field("feeds[keyword]") String feed,
                                   @Field("feeds[limit]") String limit,
                                   @Field("feeds[offset]") String offset);

}
