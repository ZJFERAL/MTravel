package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
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
    TextView mLayDateHead;
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
    private LinearLayoutManager mLayoutManager;
    private int mHeadHeight;
    private int mTop;
    private int mBottom;
    private String tempTitle;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        mID = intent.getStringExtra(FinalParams.TRIPS_DETIAL_ID);
        mHeadUrl = intent.getStringExtra(FinalParams.SPECIAL_URL);
        mTitle = intent.getStringExtra(FinalParams.SPECIAL_TITLE);
        mDes = intent.getStringExtra(FinalParams.SPECIAL_DES);
        mArticleSectionsBeanList = new ArrayList<>();
        mAdapter = new SpecialDetialAdapter(mContext,
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
        mAdapter.setEmptyView(mEmptyView);
        mLayoutManager = new LinearLayoutManager
                (mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstPosition = mLayoutManager.findFirstVisibleItemPosition();

                setTitleData(firstPosition);

                int nextPostion = firstPosition == mArticleSectionsBeanList.size() - 1 ? firstPosition : firstPosition + 1;
                ArticleSectionsBean bean = mArticleSectionsBeanList.get(nextPostion);
                String title = bean.getTitle();

                if (mHeadHeight == 0) {
                    mHeadHeight = mLayDateHead.getHeight();
                    mTop = mLayDateHead.getTop();
                    mBottom = mLayDateHead.getBottom();
                }
                if ((!TextUtils.isEmpty(title)) && (!title.equals(tempTitle))) {
                    if (mLayoutManager.getChildCount() >= 2) {
                        int top = recyclerView.getChildAt(1).getTop();
                        if (top <= mHeadHeight) {
                            mLayDateHead.setTop(mTop - (mHeadHeight - top));
                            mLayDateHead.setBottom(mBottom - (mHeadHeight - top));
                        } else {
                            mLayDateHead.setTop(mTop);
                            mLayDateHead.setBottom(mBottom);
                        }
                    }
                } else {
                    mLayDateHead.setTop(mTop);
                    mLayDateHead.setBottom(mBottom);
                }

            }
        });
    }

    private void setTitleData(int position) {
        ArticleSectionsBean bean = mArticleSectionsBeanList.get(position);
        String currentTitle = bean.getTitle();
        if (!TextUtils.isEmpty(currentTitle)) {
            mLayDateHead.setVisibility(View.VISIBLE);
            if (!currentTitle.equals(tempTitle)) {
                tempTitle = currentTitle;
                mLayDateHead.setText(tempTitle);
            }
        } else {
            mLayDateHead.setVisibility(View.GONE);
        }
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
        mAdapter.addNewData(mArticleSectionsBeen);

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
