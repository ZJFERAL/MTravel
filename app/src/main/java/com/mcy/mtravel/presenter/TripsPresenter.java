package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.index.CBannerBean;
import com.mcy.mtravel.entity.index.TripsBean;
import com.mcy.mtravel.model.TripsModel;
import com.mcy.mtravel.model.impl.TripsModelImpl;
import com.mcy.mtravel.view.impl.TripsView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;
import com.zjf.core.utils.LogUtils;

import java.util.List;

/**
 * Created by machengyuan on 2017/4/14.
 */

public class TripsPresenter extends Presenter<TripsView> {

    private TripsModelImpl mTripsModel;
    private List<CBannerBean> mCBannerBeen;

    public TripsPresenter() {
        mTripsModel = new TripsModel();
    }

    @Override
    protected void onViewCreated() {
        refreshData();
    }

    public void getMoreData() {
        mTripsModel.getData(new OnAsyncModelListener<List<TripsBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<TripsBean> msg) {
                mView.onGetMoreData(msg);
            }
        });
    }


    public void refreshData() {
        mTripsModel.getRefreshData(new OnAsyncModelListener<List<TripsBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
                LogUtils.e("TripsPresenter", "getTitles error");
            }

            @Override
            public void onSuccess(List<TripsBean> msg) {
                getBanner(msg);
            }
        });
    }

    public void getBanner(final List<TripsBean> tripsBeanList) {
        if (mCBannerBeen == null) {
            mTripsModel.getBanner(new OnAsyncModelListener<List<CBannerBean>>() {
                @Override
                public void onFailure(String msg, int type) {
                    mView.onRefreshData(tripsBeanList, null);
                    LogUtils.e("TripsPresenter", "getBanner null");
                }

                @Override
                public void onSuccess(List<CBannerBean> msg) {
                    mCBannerBeen = msg;
                    mView.onRefreshData(tripsBeanList, msg);
                }
            });
        } else {
            mView.onRefreshData(tripsBeanList, mCBannerBeen);
        }

    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        mTripsModel.cancel();
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mTripsModel = null;
        mCBannerBeen = null;
    }
}
