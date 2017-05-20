package com.zjf.core.model;

import com.zjf.core.impl.OnAsyncModelListener;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jifengZhao on 2017/5/3.
 */

public abstract class BaseModel<T> implements BaseModelImpl_Temp<T> {

    protected CompositeDisposable mCompositeDisposable;
    protected int index;
    private int initIndex = 1;


    public BaseModel() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void setInitIndex(int initIndex) {
        this.initIndex = initIndex;
    }

    @Override
    public void refreshData(OnAsyncModelListener<T> listener) {
        index = initIndex;
        getMoreData(listener);
    }



    @Override
    public void cancel() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


}
