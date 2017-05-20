package com.zjf.core.presenter;

import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseModel;
import com.zjf.core.view.BaseRecyclerViewImpl;

import java.util.List;

/**
 * Created by jifengZhao on 2017/5/3.
 */

public abstract class RecyclerViewPresenter<V extends BaseRecyclerViewImpl<B>, M extends BaseModel<List<B>>, B> extends RecyclerViewBasePresenter<V, M,B> {

    @Override
    protected void onViewCreated() {
        refreshData();
    }

    public void refreshData() {
        mModel.refreshData(new OnAsyncModelListener<List<B>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<B> msg) {
                mView.onRefreshData(msg);
            }
        });
    }

    public void getMoreData() {
        mModel.getMoreData(new OnAsyncModelListener<List<B>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<B> msg) {
                mView.onGetMoreData(msg);
            }
        });
    }
}
