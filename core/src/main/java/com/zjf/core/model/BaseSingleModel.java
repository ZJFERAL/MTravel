package com.zjf.core.model;

import com.zjf.core.impl.OnAsyncModelListener;

/**
 * Created by zhaojifeng on 2017/5/5.
 */

public abstract class BaseSingleModel<T> extends BaseModel<T> {

    @Override
    public void getMoreData(OnAsyncModelListener<T> listener) {
        return;
    }

    @Override
    public void refreshData(OnAsyncModelListener<T> listener) {
        getData(listener);
    }

    protected abstract void getData(OnAsyncModelListener<T> listener);
}