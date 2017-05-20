package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.SpecialDetialAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.special.ArticleSectionsBean;
import com.mcy.mtravel.entity.special.AttractionBean;
import com.mcy.mtravel.presenter.SpecialDetialPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.SpecialDetialView;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialDetialActivity extends MVPActivity<SpecialDetialPresenter> implements SpecialDetialView {

    @BindView(R.id.img_cover)
    ImageView mImgCover;
    @BindView(R.id.txt_title)
    TextView mTxtTitle;
    @BindView(R.id.txt_desc)
    TextView mTxtDesc;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.txt_day_num)
    TextView mTxtDayNum;
    @BindView(R.id.empty_view)
    FrameLayout mEmptyView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;
    private String mID;
    private String mHeadUrl;
    private String mTitle;
    private String mDes;
    private List<ArticleSectionsBean> mArticleSectionsBeanList;
    private SpecialDetialAdapter mAdapter;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        mID = intent.getStringExtra(FinalParams.TRIPS_DETIAL_ID);
        mHeadUrl = intent.getStringExtra(FinalParams.SPECIAL_URL);
        mTitle = intent.getStringExtra(FinalParams.SPECIAL_TITLE);
        mDes = intent.getStringExtra(FinalParams.SPECIAL_DES);
        mArticleSectionsBeanList = new ArrayList<>();
        mAdapter = new SpecialDetialAdapter(this,
                mArticleSectionsBeanList, R.layout.item_specila_detial_item);
        super.initVariables();
    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_special_detial);
        ButterKnife.bind(this);
        mToolbar.setTitle(mTitle);
        mCollapsingToolbar.setTitleEnabled(false);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager
                (mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerview.setAdapter(mAdapter);
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
    public SpecialDetialPresenter create() {
        return new SpecialDetialPresenter(mID);
    }

    @Override
    public void getData(List<ArticleSectionsBean> mArticleSectionsBeen, List<AttractionBean> mAttractionBeen) {
        mAdapter.setAttractionBeen(mAttractionBeen);
        int size = mArticleSectionsBeen.size();
        LogUtils.e("" + size);
        mAdapter.addNewData(mArticleSectionsBeanList);

        int width = DeviceUtils.getDeviceScreenWidth(this);
        mImgCover.setLayoutParams(new RelativeLayout.LayoutParams(width, (int) (width / 1.7)));
        Glide.with(mContext)
                .load(mHeadUrl)
                .placeholder(R.drawable.weit_place)
                .into(mImgCover);
        mTxtTitle.setText(mTitle);
        mTxtDesc.setText(mDes);
    }

}
