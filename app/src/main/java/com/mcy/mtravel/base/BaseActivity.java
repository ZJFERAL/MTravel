package com.mcy.mtravel.base;

import android.os.Bundle;

import com.mcy.mtravel.utils.SPUtils;

/**
 * Created by zhaojifeng on 2017/4/13.
 */

public abstract class BaseActivity extends com.zjf.core.view.BaseActivity {

    protected SPUtils mSPUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSPUtils = new SPUtils();
        super.onCreate(savedInstanceState);
    }


}
