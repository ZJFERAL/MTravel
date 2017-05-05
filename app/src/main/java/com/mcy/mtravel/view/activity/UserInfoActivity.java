package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.TripsAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.index.TripsBean;
import com.mcy.mtravel.entity.user.UserWithTripsBean;
import com.mcy.mtravel.presenter.UserInfoPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.UserInfoView;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.ImageUtils;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends MVPActivity<UserInfoPresenter> implements UserInfoView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img_user)
    ImageView mImgUser;
    @BindView(R.id.txt_user_name)
    TextView mTxtUserName;
    @BindView(R.id.img_sex)
    ImageView mImgSex;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.empty_view)
    FrameLayout mEmptyView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;
    @BindView(R.id.refreshview)
    SwipeRefreshLayout mRefreshview;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    private String mID;
    private TripsAdapter mAdapter;
    private List<TripsBean> mBeanList;
    private boolean isFirst = true;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mID = data.getString(FinalParams.USER_ID);
            mBeanList = new ArrayList<>();
            mAdapter = new TripsAdapter(mContext, mBeanList, R.layout.item_trips);
            mAdapter.setShowUser(false);
        } else {
            showToast(getString(R.string.error_user));
        }
        super.initVariables();

    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        mRefreshview.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerview.setAdapter(mAdapter);
    }

    CRecyclerViewAdapter.LLM_RefreshListenter listener = new CRecyclerViewAdapter.LLM_RefreshListenter() {
        @Override
        protected void loadMore() {
            mPresenter.getMoreData();
        }
    };

    @Override
    public void setListener() {
        mRefreshview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefreshData();
            }
        });
        mRecyclerview.addOnScrollListener(listener);
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBg, msg, type).show();
    }

    @Override
    public UserInfoPresenter create() {
        return new UserInfoPresenter(mID);
    }

    @Override
    public void onRefreshData(UserWithTripsBean data) {
        mAdapter.flushData(data.getTrips());
        if (isFirst) {
            setTilte(data);
            isFirst = false;
        } else {
            onCloseSwipe();
        }

    }

    private void setTilte(UserWithTripsBean data) {
        Glide.with(mContext)
                .load(data.getImage())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable drawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                        mImgUser.setImageDrawable(drawable);
                        setTitleBackColor(drawable);
                        return true;
                    }
                })
                .placeholder(R.drawable.ic_insert_emoticon_orange_a200_48dp)
                .into(mImgUser);
        int width = DeviceUtils.getDeviceScreenWidth(mContext);
        mImgUser.setLayoutParams(new LinearLayout.LayoutParams(width / 8, width / 8));
        if (data.getGender() == 0) {
            mImgSex.setImageResource(R.drawable.female);
        }
        mTxtUserName.setText(data.getName() + "\n" + data.getTrips_count() + "篇游记");
        mToolbar.setTitle(data.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setTitleBackColor(Drawable drawable) {

        try {
            Palette.Builder builder = Palette.from(ImageUtils.drawableToBitmap(drawable));
            builder.generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch swatch = palette.getLightMutedSwatch();
                    LogUtils.e("onGenerated");
                    if (swatch == null) {
                        LogUtils.e("swatch != null");
                        swatch = palette.getLightVibrantSwatch();
                        if (swatch == null) {
                            swatch = palette.getDarkMutedSwatch();
                            if (swatch == null) {
                                swatch = palette.getDarkVibrantSwatch();
                                if (swatch == null) {
                                    swatch = palette.getDominantSwatch();
                                }
                            }
                        }
                    }
                    if (swatch != null) {
                        mAppbarLayout.setBackgroundColor(swatch.getRgb());
                        mCollapsingToolbar.setStatusBarScrimColor(swatch.getRgb());
                        mCollapsingToolbar.setContentScrimColor(swatch.getRgb());
                        mTxtUserName.setTextColor(swatch.getTitleTextColor());
                        mToolbar.setTitleTextColor(swatch.getTitleTextColor());
                    }

                }
            });
        } catch (Exception e) {
            LogUtils.e("setTitleBackColor", e.getMessage());
        } finally {
            onCloseSwipe();
        }
    }

    @Override
    public void onGetMoreData(UserWithTripsBean data) {
        mAdapter.addNewData(data.getTrips());
        onCloseSwipe();
    }

    @Override
    public void onFailure(String msg, int type) {
        if (msg.equals(getString(R.string.no_more_data))) {
            mRecyclerview.removeOnScrollListener(listener);
        }
        onCloseSwipe();
        try {
            showSnakBar(msg, type);
        } catch (Exception e) {
            showToast(msg);
        }
    }

    private void onCloseSwipe() {
        if (mAdapter != null) {
            mAdapter.onCompleteLoading();
        }
        if (mRefreshview != null) {
            mRefreshview.setRefreshing(false);
        }
        mAppbarLayout.setVisibility(View.VISIBLE);
    }

}
