package com.zjf.core.presenter;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * @author :ZJF
 * @version : 2016-12-16 下午 4:17
 */

public class PresenterLoader<T extends BasePresenter> extends Loader<T> {
    private T mPresenter;
    private PresenterFactory<T> mFactory;

    public PresenterLoader(Context context, PresenterFactory<T> factory) {
        super(context);
        mFactory = factory;
    }

    @Override
    protected void onStartLoading() {
        if(mPresenter!=null){
            deliverResult(mPresenter);
            return;
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        mPresenter = mFactory.create();
        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        this.mPresenter = null;
    }
}
