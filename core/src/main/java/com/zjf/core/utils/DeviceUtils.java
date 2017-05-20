package com.zjf.core.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by machengyuan on 2017/4/13.
 */

public class DeviceUtils {

    /**
     * 获取设备ID
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        String deviceId;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = tm.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        }
        return deviceId;
    }


    /**
     * 获取设备屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getDeviceScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取设备屏幕高度
     *
     * @param context
     * @return
     */
    public static int getDeviceScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}
