package com.zjf.core.view;

import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.presenter.BasePresenter;

import java.util.List;


public abstract class MVPRecyclerViewActivity<T extends BasePresenter, B, A extends CRecyclerViewAdapter<B>> extends MVPActivity<T> implements BaseRecyclerViewImpl<B> {

    protected A mAdapter;
    protected List<B> mList;

    @Override
    public void initVariables() {
        mAdapter = onCreateAdapter();
        super.initVariables();
    }

    protected abstract A onCreateAdapter();



    @Override
    public void onGetMoreData(List<B> data) {
        mAdapter.flushData(data);
        onComplate();
    }

    @Override
    public void onRefreshData(List<B> data) {
        mAdapter.addNewData(data);
        onComplate();
    }

    @Override
    public void onComplate() {
        if (mAdapter != null) {
            mAdapter.onCompleteLoading();
        }
    }
}
