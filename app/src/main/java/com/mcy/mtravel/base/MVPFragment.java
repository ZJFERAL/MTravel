package com.mcy.mtravel.base;

import com.mcy.mtravel.utils.SPUtils;
import com.zjf.core.presenter.BasePresenter;

/**
 * Created by jifengZhao on 2017/4/14.
 */

public abstract class MVPFragment<T extends BasePresenter> extends com.zjf.core.view.MVPFragment<T> {

    private SPUtils mSPUtils;

    @Override
    public void initVariables() {
        mSPUtils = new SPUtils();
    }
}
