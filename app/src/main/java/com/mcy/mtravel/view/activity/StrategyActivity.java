package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.StrategyAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.StrategyBean;
import com.mcy.mtravel.presenter.StrategyPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.StrategyView;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StrategyActivity extends MVPActivity<StrategyPresenter> implements StrategyView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.refreshview)
    SwipeRefreshLayout mRefreshview;
    @BindView(R.id.empty_view)
    FrameLayout mEmptyView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;

    private String mID;
    private StrategyAdapter mAdapter;
    private List<StrategyBean> mBeanList;
    private boolean isFirst = true;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mID = data.getString(FinalParams.STRATEGY_ID);
            mBeanList = new ArrayList<>();
            mAdapter = new StrategyAdapter(mContext, mBeanList, R.layout.item_strategy);
        } else {
            showToast(getString(R.string.error_place));
        }
        super.initVariables();

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_strategy);
        ButterKnife.bind(this);
        mRefreshview.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        mRefreshview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onGetData();
            }
        });
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBg, msg, type);
    }

    @Override
    public StrategyPresenter create() {
        return new StrategyPresenter(mID);
    }

    @Override
    public void onRefreshData(List<StrategyBean> data) {
        mAdapter.flushData(data);
        if (isFirst) {
            isFirst = false;
            mToolbar.setTitle(data.get(0).getName_zh_cn());
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        onCloseSwipe();
    }

    @Override
    public void onFailure(String msg, int type) {
        onCloseSwipe();
        try {
            showSnakBar(msg, type);
        } catch (Exception e) {
            showToast(msg);
        }
    }

    private void onCloseSwipe() {
        if (mRefreshview != null) {
            mRefreshview.setRefreshing(false);
        }
        mAppbarLayout.setVisibility(View.VISIBLE);
    }

}
