package com.mcy.mtravel.base;

import com.mcy.mtravel.utils.SPUtils;
import com.zjf.core.presenter.BasePresenter;

/**
 * Created by machengyuan on 2017/4/14.
 */

public abstract class MVPActivity<T extends BasePresenter> extends com.zjf.core.view.MVPActivity<T> {

    protected SPUtils mSPUtils;

    @Override
    public void initVariables() {
        super.initVariables();
        mSPUtils = new SPUtils();
    }
}
