package com.zjf.core.view;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.zjf.core.presenter.BasePresenter;
import com.zjf.core.presenter.PresenterFactory;
import com.zjf.core.presenter.PresenterLoader;

/**
 * @author :ZJF
 * @version : 2016-12-19 下午 5:40
 */

public abstract class MVPFragment<T extends BasePresenter> extends BaseFragment implements LoaderManager.LoaderCallbacks<T>, PresenterFactory<T> {

    protected T mPresenter;

    public MVPFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onViewAttached(this);
    }

    @Override
    public void loaderData() {
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<T> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(getContext(), this);
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
    public void onStop() {
        super.onStop();
        mPresenter.onViewDeached();
    }
}
