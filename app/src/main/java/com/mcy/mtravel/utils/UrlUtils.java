package com.mcy.mtravel.utils;

import android.text.TextUtils;

import com.mcy.mtravel.App;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.LogUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zhaojifeng on 2017/4/13.
 */

public class UrlUtils {

    public static String getTokenAuthorization(boolean isFirst) {

        String deviceId = DeviceUtils.getDeviceId(App.getInstance());
        String authorization = "device/register";
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(authorization).append(System.getProperty("line.separator"));
        try {
            localStringBuffer.append(URLEncoder.encode("app_id", "UTF-8"))
                    .append('=')
                    .append(URLEncoder.encode("com.qyer.android.qyerguide", "UTF-8"))
                    .append('&')
                    .append(URLEncoder.encode("app_version", "UTF-8"))
                    .append('=')
                    .append(URLEncoder.encode("1.9.5", "UTF-8"))
                    .append('&')
                    .append(URLEncoder.encode("device_id", "UTF-8"))
                    .append('=')
                    .append(URLEncoder.encode(deviceId, "UTF-8"));
            if (isFirst) {
                localStringBuffer.append('&')
                        .append(URLEncoder.encode("init", "UTF-8"))
                        .append('=')
                        .append(URLEncoder.encode("1", "UTF-8"));
            }
            SPUtils spUtils = new SPUtils();
            if (!TextUtils.isEmpty(spUtils.getToken())) {
                localStringBuffer.append("\n").append(spUtils.getToken());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        authorization = EncryptUtils.encode(localStringBuffer.toString());
        LogUtils.e("author", authorization);
        return authorization;
    }

    public static String getIndexAuthorization(int page) {
        int offset = page * 10;
        String authorization = "recommend-rotator:slide,news-list:feeds,config-indexad:adverts";
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(authorization).append(System.getProperty("line.separator"));
        try {
            localStringBuffer.append(URLEncoder.encode("feeds[keyword]", "UTF-8"))
                    .append('=')
                    .append(URLEncoder.encode("feed", "UTF-8"))
                    .append('&')
                    .append(URLEncoder.encode("feeds[limit]", "UTF-8"))
                    .append('=')
                    .append(URLEncoder.encode("10", "UTF-8"))
                    .append('&')
                    .append(URLEncoder.encode("feeds[offset]", "UTF-8"))
                    .append('=')
                    .append(URLEncoder.encode("" + offset, "UTF-8"));

            SPUtils spUtils = new SPUtils();
            if (!TextUtils.isEmpty(spUtils.getToken())) {
                localStringBuffer.append("\n").append(spUtils.getToken());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        authorization = EncryptUtils.encode(localStringBuffer.toString());
        LogUtils.e("author", authorization);
        return authorization;
    }

}
