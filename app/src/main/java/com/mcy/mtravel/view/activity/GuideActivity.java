package com.mcy.mtravel.view.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mcy.mtravel.R;
import com.mcy.mtravel.base.BaseActivity;
import com.zjf.core.adapter.ImageViewPagerAdapter;
import com.zjf.core.utils.SizeUtils;
import com.zjf.core.widget.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.btn_enter)
    Button mBtnEnter;
    @BindView(R.id.circleindicator)
    CircleIndicator mIndicator;

    private ImageViewPagerAdapter mAdapter;
    private List<ImageView> mViewList;

    @Override
    public void initVariables() {
        mViewList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            int identifier = getResources().getIdentifier("ic_splash_guide_bg" + i, "drawable", getPackageName());
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new ViewPager.LayoutParams());
            Glide.with(this).load(identifier).into(imageView);
            mViewList.add(imageView);
        }
        mAdapter = new ImageViewPagerAdapter(mViewList);
    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        mIndicator.setIndicatorMode(CircleIndicator.Mode.OUTSIDE);
        mIndicator.setIndicatorRadius(SizeUtils.dp2px(3, this));//圆的大小
        mIndicator.setIndicatorMargin(SizeUtils.dp2px(5, this));//间隔
        mIndicator.setViewPager(mViewpager, mViewList.size());
        mViewpager.setAdapter(mAdapter);


    }

    @Override
    public void setListener() {
        mViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            float lastPositionOffset = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 2) {
                    mBtnEnter.setClickable(false);
                    if (lastPositionOffset < positionOffset - 0.001) {
                        if (mBtnEnter.getVisibility() == View.GONE) {
                            mBtnEnter.setVisibility(View.VISIBLE);
                        }
                        mBtnEnter.setAlpha(positionOffset);
                    } else {
                        mBtnEnter.setAlpha(positionOffset);
                    }
                    if (positionOffset == 0) {
                        mBtnEnter.setVisibility(View.GONE);
                    }
                    lastPositionOffset = positionOffset;
                } else if (position == 3) {
                    mBtnEnter.setClickable(true);
                }
            }
        });

        mBtnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(GuideActivity.this, MainActivity.class, true);
            }
        });
    }

}
