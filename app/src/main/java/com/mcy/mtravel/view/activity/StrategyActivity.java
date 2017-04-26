package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.StrategyBean;
import com.mcy.mtravel.presenter.StrategyPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.StrategyView;

import java.util.List;

public class StrategyActivity extends MVPActivity<StrategyPresenter> implements StrategyView {


    private String mID;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mID = data.getString(FinalParams.STRATEGY_ID);
//            mBeanList = new ArrayList<>();
//            mAdapter = new TripsAdapter(mContext, mBeanList, R.layout.item_trips);
//            mAdapter.setShowUser(false);
        } else {
            showToast(getString(R.string.error_place));
        }
        super.initVariables();

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_strategy);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void showSnakBar(String msg, int type) {

    }

    @Override
    public StrategyPresenter create() {
        return null;
    }

    @Override
    public void onRefreshData(List<StrategyBean> data) {

    }

    @Override
    public void onFailure(String msg, int type) {

    }

}
