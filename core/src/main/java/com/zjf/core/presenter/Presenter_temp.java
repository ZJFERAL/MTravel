package com.zjf.core.presenter;


import com.zjf.core.model.BaseModel;

public abstract class Presenter_temp<V, M extends BaseModel> implements BasePresenter<V> {

    protected V mView;
    public boolean isAttached = false;
    protected M mModel;


    @Override
    public void onCreate(V view) {
        this.mView = view;
        onViewCreated();
    }

    @Override
    public void onViewAttached() {
        isAttached = true;
    }


    protected abstract void onViewCreated();

    @Override
    public void onViewDeached() {
        isAttached = false;
        if (mModel != null) {
            mModel.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        isAttached = false;
        mView = null;
        mModel = null;
    }


}
