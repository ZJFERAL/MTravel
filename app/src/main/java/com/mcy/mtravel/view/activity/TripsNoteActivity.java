package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.presenter.TripsNotePresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.TripsNoteView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripsNoteActivity extends MVPActivity<TripsNotePresenter> implements TripsNoteView {

    @BindView(R.id.img_cover)
    ImageView mImgCover;
    @BindView(R.id.txt_title)
    TextView mTxtTitle;
    @BindView(R.id.txt_time)
    TextView mTxtTime;
    @BindView(R.id.img_user)
    ImageView mImgUser;
    @BindView(R.id.txt_data)
    TextView mTxtData;
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
    LinearLayout mEmptyView;


    private String mTripsID;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mTripsID = data.getString(FinalParams.TRIPS_NOTE_ID);
        }
        super.initVariables();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_trips_note);
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void showSnakBar(String msg, int type) {

    }

    @Override
    public TripsNotePresenter create() {
        return new TripsNotePresenter(mTripsID);
    }

}
