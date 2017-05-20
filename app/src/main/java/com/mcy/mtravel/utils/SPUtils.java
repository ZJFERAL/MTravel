package com.mcy.mtravel.utils;

import com.mcy.mtravel.App;

/**
 * Created by jifengZhao on 2017/4/13.
 */

public class SPUtils extends com.zjf.core.utils.SPUtils {

    /**
     * SPUtils构造函数
     * <p>在Application中初始化</p>
     */
    public SPUtils() {
        super("config", App.getInstance());
    }

    public String getToken() {
        return getString("token", "");
    }

    public void saveToken(String token) {
        put("token", token);
    }

    public void changeFirstFlag() {
        put("isFirst", false);
    }

    public boolean getFirstFlag() {
        return getBoolean("isFirst", true);
    }
}
