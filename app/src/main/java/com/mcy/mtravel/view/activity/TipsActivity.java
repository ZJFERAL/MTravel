package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.TipMenuAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.tipwiki.StrategyDetialBean;
import com.mcy.mtravel.presenter.TipPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.TipView;
import com.zjf.core.utils.SnackBarUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipsActivity extends MVPActivity<TipPresenter> implements TipView {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.listview)
    ListView mListView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;

    private String mID;
    private TipMenuAdapter mAdapter;
    private String mTitle;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("data");
            mID = bundle.getString(FinalParams.TIP_ID);
            mTitle = bundle.getString(FinalParams.TIP_TITLE);
        } else {
            showToast(getString(R.string.error_place));
        }
        super.initVariables();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_tips);
        ButterKnife.bind(this);
        mToolbar.setTitle(mTitle + getString(R.string.tip));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView.setEmptyView(mEmptyView);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBg, msg, type).show();
    }

    @Override
    public void onFailure(String msg, int type) {
        try {
            showSnakBar(msg, type);
        } catch (Exception e) {
            showToast(msg);
        }
    }

    @Override
    public TipPresenter create() {
        return new TipPresenter(mID);
    }

    @Override
    public void onGetData(List<StrategyDetialBean> list) {
        mAdapter = new TipMenuAdapter(mContext, list, R.layout.item_tipmenu);
        mAdapter.setID(mID);
        mListView.setAdapter(mAdapter);
    }
}
