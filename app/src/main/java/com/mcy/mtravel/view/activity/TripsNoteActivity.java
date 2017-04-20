package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.ExpandableAdapter;
import com.mcy.mtravel.adapter.TripsNoteAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.NotesBean;
import com.mcy.mtravel.entity.TripsBean;
import com.mcy.mtravel.presenter.TripsNotePresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.TripsNoteView;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripsNoteActivity extends MVPActivity<TripsNotePresenter> implements TripsNoteView {

    @BindView(R.id.img_cover)
    ImageView mImgCover;
    @BindView(R.id.img_user)
    ImageView mImgUser;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewfliper_data)
    ViewFlipper mViewfliperData;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;
    @BindView(R.id.expand_listview)
    ExpandableListView mExpandListview;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.empty_view)
    FrameLayout mEmptyView;


    private String mTripsID;
    private ExpandableAdapter mExpandableAdapter;
    private List<NotesBean> mNotesBeen;
    private TripsNoteAdapter mAdapter;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mTripsID = data.getString(FinalParams.TRIPS_NOTE_ID);
            LogUtils.e("mTripsID", mTripsID);
        }
        super.initVariables();
        mNotesBeen = new ArrayList<>();
        mAdapter = new TripsNoteAdapter(mContext, mNotesBeen, R.layout.item_trips_notes);

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_trips_note);
        ButterKnife.bind(this);
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
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
    public TripsNotePresenter create() {
        return new TripsNotePresenter(mTripsID);
    }

    @Override
    public void onFailure(String msg, int type) {
        showSnakBar(msg, type);
        onCloseSwipe();
    }

    private void onCloseSwipe() {
        if (mAdapter != null) {
            mAdapter.onCompleteLoading();
        }
    }

    @Override
    public void onLeftView(List<String> dates, List<List<String>> items) {
        mExpandableAdapter = new ExpandableAdapter(dates, items, mContext);
        mExpandListview.setAdapter(mExpandableAdapter);
    }

    @Override
    public void onRecyclerViewData(List<NotesBean> notesBeanList) {
        mAdapter.flushData(notesBeanList);
        onCloseSwipe();
    }

    @Override
    public void onTitleData(TripsBean bean) {
        int width = DeviceUtils.getDeviceScreenWidth(mContext);
        Glide.with(mContext)
                .load(bean.getFront_cover_photo_url())
                .placeholder(R.drawable.weit_place)
                .into(mImgCover);
        mImgCover.setLayoutParams(new RelativeLayout.LayoutParams(width, width / 2));
        Glide.with(mContext)
                .load(bean.getUser().getImage())
                .placeholder(R.drawable.weit_place)
                .into(mImgUser);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width / 9, width / 9);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mImgUser.setLayoutParams(params);
        mToolbar.setTitle(bean.getName());
        mToolbar.setSubtitle(bean.getStart_date() + "/" + bean.getDays() + "天," + bean.getPhotos_count() + "图");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
