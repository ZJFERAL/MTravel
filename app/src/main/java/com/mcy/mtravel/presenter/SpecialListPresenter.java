package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.tip.SpecialListBean;
import com.mcy.mtravel.model.SpecialModel;
import com.mcy.mtravel.model.impl.SpecialModelImpl;
import com.mcy.mtravel.view.impl.SpecialListView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public class SpecialListPresenter extends Presenter<SpecialListView> {

    private SpecialModelImpl mModel;
    private String mID;

    public SpecialListPresenter(String ID) {
        mID = ID;
        mModel = new SpecialModel(mID);
    }

    @Override
    protected void onViewCreated() {
        onRefreshData();
    }

    public void onRefreshData() {
        mModel.onRefreshData(new OnAsyncModelListener<List<SpecialListBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<SpecialListBean> msg) {
                mView.onRefreshData(msg);
            }
        });
    }

    public void getMoreData() {
        mModel.getData(new OnAsyncModelListener<List<SpecialListBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<SpecialListBean> msg) {
                mView.onGetMoreData(msg);
            }
        });
    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        if (mModel != null) {
            mModel.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mModel = null;
    }
}
