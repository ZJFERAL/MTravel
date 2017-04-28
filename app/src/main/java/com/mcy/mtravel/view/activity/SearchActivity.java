package com.mcy.mtravel.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.title_search)
    EditText mTitleSearch;
    @BindView(R.id.img_search)
    ImageView mImgSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;


    @Override
    public void initVariables() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @Override
    public void setListener() {

    }
}
