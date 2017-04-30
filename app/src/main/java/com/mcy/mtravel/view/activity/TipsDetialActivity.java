package com.mcy.mtravel.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.ChildrenBean;
import com.mcy.mtravel.presenter.TipsDetialPresenter;
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

    @Override
    public void initVariables() {

        super.initVariables();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_tips_detial);
        ButterKnife.bind(this);
        mExpandListview.setEmptyView(mEmptyView);
    }

    @Override
    public void setListener() {

    }


    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBg, msg, type);
    }

    @Override
    public void onFailure(String msg, int type) {

    }

    @Override
    public TipsDetialPresenter create() {
        return null;
    }

    @Override
    public void onGetData(List<ChildrenBean> list) {

    }
}
