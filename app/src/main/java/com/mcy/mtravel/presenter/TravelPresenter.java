package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.tip.TravelListBean;
import com.mcy.mtravel.model.TravelModel;
import com.mcy.mtravel.model.impl.TravelModelImpl;
import com.mcy.mtravel.view.impl.TravelView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public class TravelPresenter extends Presenter<TravelView> {

    private TravelModelImpl mModel;
    private String mID;

    public TravelPresenter(String ID) {
        mID = ID;
        mModel = new TravelModel(mID);
    }

    @Override
    protected void onViewCreated() {
        onRefreshData();
    }

    public void onRefreshData() {
        mModel.onRefreshData(new OnAsyncModelListener<List<TravelListBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<TravelListBean> msg) {
                mView.onRefreshData(msg);
            }
        });
    }

    public void getMoreData() {
        mModel.getData(new OnAsyncModelListener<List<TravelListBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<TravelListBean> msg) {
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
