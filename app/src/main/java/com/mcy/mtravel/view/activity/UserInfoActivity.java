package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.UserWithTripsBean;
import com.mcy.mtravel.presenter.UserInfoPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.UserInfoView;
import com.zjf.core.utils.SnackBarUtils;

public class UserInfoActivity extends MVPActivity<UserInfoPresenter> implements UserInfoView {

    private String mID;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mID = data.getString(FinalParams.USER_ID);
        }
        super.initVariables();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_user_info);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(null, msg, type).show();
    }

    @Override
    public UserInfoPresenter create() {
        return new UserInfoPresenter(mID);
    }

    @Override
    public void onRefreshData(UserWithTripsBean data) {

    }

    @Override
    public void onGetMoreData(UserWithTripsBean data) {

    }

    @Override
    public void onFailure(String msg, int type) {
        onCloseSwipe();
        showSnakBar(msg, type);
    }

    private void onCloseSwipe() {
//        if (mAdapter != null) {
//            mAdapter.onCompleteLoading();
//        }
    }
}
