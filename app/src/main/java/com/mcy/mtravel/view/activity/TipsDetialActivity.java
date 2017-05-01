package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.TipsDetialAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.ChildrenBean;
import com.mcy.mtravel.presenter.TipsDetialPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.TipsDetialView;
import com.zjf.core.utils.SnackBarUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipsDetialActivity extends MVPActivity<TipsDetialPresenter> implements TipsDetialView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.expand_listview)
    ExpandableListView mExpandListview;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;

    private String mID, mTitle, mChildTitle;
    private int mChildPos = -1, mGroupPos = -1;
    private TipsDetialAdapter mAdapter;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("data");
            mID = bundle.getString(FinalParams.TIP_ID);
            mTitle = bundle.getString(FinalParams.TIP_TITLE);
            mChildTitle = bundle.getString(FinalParams.TIPS_DETIAL_CHILD_TITLE);
            mGroupPos = bundle.getInt(FinalParams.TIPS_DETIAL_GROUP_POSITION, -1);
            mChildPos = bundle.getInt(FinalParams.TIPS_DETIAL_CHILD_POSITION, -1);
        } else {
            showToast(getString(R.string.error_place));
        }
        super.initVariables();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_tips_detial);
        ButterKnife.bind(this);
        mExpandListview.setEmptyView(mEmptyView);
        mExpandListview.setGroupIndicator(null);
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public TipsDetialPresenter create() {
        TipsDetialPresenter presenter = null;
        if (!TextUtils.isEmpty(mChildTitle)) {
            presenter = new TipsDetialPresenter(mID, mChildTitle);
        } else if (mChildPos != -1 && mGroupPos != -1) {
            presenter = new TipsDetialPresenter(mID, mGroupPos, mChildPos);
        } else {
            throw new IllegalStateException("you must call add pos or childTitle  first");
        }
        return presenter;
    }

    @Override
    public void onGetData(List<ChildrenBean> list) {
        mAdapter = new TipsDetialAdapter(list, this);
        mExpandListview.setAdapter(mAdapter);
        mExpandListview.expandGroup(0);
    }
}
