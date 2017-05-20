package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.tip.TipTripsBean;
import com.mcy.mtravel.model.TipTripsListModel;
import com.mcy.mtravel.model.impl.TipTripsListModelImpl;
import com.mcy.mtravel.view.impl.TipTripsListView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

import java.util.List;

/**
 * Created by jifengZhao on 2017/5/3.
 */

public class TipTripsListPresenter extends Presenter<TipTripsListView> {

    private TipTripsListModelImpl mModel;
    private String mTravelID;

    public TipTripsListPresenter(String travelID) {
        mTravelID = travelID;
        mModel = new TipTripsListModel(mTravelID);
    }

    @Override
    protected void onViewCreated() {
        onRefreshData();
    }

    public void onRefreshData() {
        mModel.onRefreshData(new OnAsyncModelListener<List<TipTripsBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<TipTripsBean> msg) {
                mView.onRefreshData(msg);
            }
        });
    }

    public void getMoreData() {
        mModel.getData(new OnAsyncModelListener<List<TipTripsBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<TipTripsBean> msg) {
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
