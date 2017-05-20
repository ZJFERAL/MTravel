package com.mcy.mtravel.presenter;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.user.UserWithTripsBean;
import com.mcy.mtravel.model.UserInfoModel;
import com.mcy.mtravel.model.impl.UserInfoModelImpl;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.UserInfoView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

/**
 * Created by jifengZhao on 2017/4/21.
 */

public class UserInfoPresenter extends Presenter<UserInfoView> {

    private UserInfoModelImpl mInfoModel;
    private String mUserID;

    public UserInfoPresenter(String userID) {
        this.mUserID = userID;
        mInfoModel = new UserInfoModel(userID);
    }

    @Override
    protected void onViewCreated() {
        onRefreshData();
    }

    public void onRefreshData() {
        mInfoModel.onRefreshData(new OnAsyncModelListener<UserWithTripsBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(UserWithTripsBean msg) {
                mView.onRefreshData(msg);
            }
        });
    }

    public void getMoreData() {
        mInfoModel.getData(new OnAsyncModelListener<UserWithTripsBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(UserWithTripsBean msg) {
                if (msg.getTrips() == null || msg.getTrips().size() == 0) {
                    mView.onFailure(App.getStringRes(R.string.no_more_data), FinalParams.ERROR_INFO);
                }
                mView.onGetMoreData(msg);
            }
        });
    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        if (mInfoModel != null) {
            mInfoModel.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mInfoModel = null;
    }
}
