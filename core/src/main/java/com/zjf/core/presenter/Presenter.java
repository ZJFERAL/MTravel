package com.zjf.core.presenter;


public abstract class Presenter<V> implements BasePresenter<V> {

    protected V mView;
    public boolean isAttached = false;


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
    }

    @Override
    public void onDestroyed() {
        isAttached = false;
        mView = null;
    }
}
