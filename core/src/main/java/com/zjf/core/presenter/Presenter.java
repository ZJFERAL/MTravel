package com.zjf.core.presenter;


public abstract class Presenter<V> implements BasePresenter<V> {

    protected V mView;
    protected boolean isAttached = false;


    @Override
    public void onViewAttached(V view) {
        this.mView = view;
        isAttached = true;
        onViewStart();
    }

    protected abstract void onViewStart();

    @Override
    public void onViewDeached() {
        isAttached = false;
    }

    @Override
    public void onDestroyed() {
        isAttached = false;
        mView = null;
    }
}
