package com.zjf.core.presenter;

import com.zjf.core.model.BaseModel;
import com.zjf.core.view.BaseRecyclerViewImpl;

/**
 * Created by zhaojifeng on 2017/5/5.
 */

public abstract class RecyclerViewBasePresenter<V extends BaseRecyclerViewImpl<B>, M extends BaseModel, B> extends Presenter_temp<V, M> {

    @Override
    protected void onViewCreated() {
        refreshData();
    }

    public abstract void refreshData();

    public abstract void getMoreData();
}
