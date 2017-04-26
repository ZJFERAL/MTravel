package com.mcy.mtravel;

import com.zjf.core.MyApplication;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.SnackBarUtils;

/**
 * Created by zhaojifeng on 2017/4/11.
 */

public class App extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.isDebug = true;
        SnackBarUtils.colorAccent = getResources().getColor(R.color.colorAccent);
    }
}
