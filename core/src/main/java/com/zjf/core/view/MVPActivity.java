package com.zjf.core.view;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.zjf.core.presenter.BasePresenter;
import com.zjf.core.presenter.PresenterFactory;
import com.zjf.core.presenter.PresenterLoader;


public abstract class MVPActivity<T extends BasePresenter> extends BaseActivity implements LoaderManager.LoaderCallbacks<T>, PresenterFactory<T>{

    protected T mPresenter;

    @Override
    public void initVariables() {
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<T> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, this);
    }


    @Override
    public void onLoadFinished(Loader<T> loader, T data) {
        this.mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<T> loader) {
        this.mPresenter.onDestroyed();
        this.mPresenter = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onViewAttached(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onViewDeached();
    }
}
