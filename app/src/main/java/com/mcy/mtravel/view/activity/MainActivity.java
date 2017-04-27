package com.mcy.mtravel.view.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.BaseActivity;
import com.mcy.mtravel.view.fragment.NewFragment;
import com.mcy.mtravel.view.fragment.PlanFragment;
import com.mcy.mtravel.view.fragment.TripsFragment;
import com.zjf.core.adapter.TabAdapter;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBG;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.title_search)
    TextView mTitleView;
    @BindView(R.id.img_about)
    ImageView mBtnAbout;

    private TabAdapter mAdapter;
    private List<Fragment> mFragmentList;
    private String[] titls;
    private long currentTime;


    @Override
    public void initVariables() {
        mFragmentList = new ArrayList<>();
        makeFragment();
        titls = new String[]{getString(R.string.travelnote), getString(R.string.news), getString(R.string.plan)};
        mAdapter = new TabAdapter(getSupportFragmentManager(), mFragmentList, titls);
    }

    private void makeFragment() {
        TripsFragment noteFragment = new TripsFragment();
        mFragmentList.add(noteFragment);
        NewFragment newFragment = new NewFragment();
        mFragmentList.add(newFragment);
        PlanFragment planFragment = new PlanFragment();
        mFragmentList.add(planFragment);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setAdapter(mAdapter);
        mViewpager.setCurrentItem(1, false);
    }

    @Override
    public void setListener() {
        mTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(MainActivity.this, AboutActivity.class, false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        long timeMillis = System.currentTimeMillis();
        if (timeMillis - currentTime > 2000) {
            currentTime = timeMillis;
            showToast(getString(R.string.next_exit));
        } else {
            super.onBackPressed();
        }
    }

    private void showSnackBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBG, msg, type).show();
    }


}
