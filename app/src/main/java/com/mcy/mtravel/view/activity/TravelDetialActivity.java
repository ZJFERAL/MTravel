package com.mcy.mtravel.view.activity;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.TravelDetialAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.travel.AttractionContentsBean;
import com.mcy.mtravel.entity.travel.AttractionsBean;
import com.mcy.mtravel.entity.travel.HotelsBean;
import com.mcy.mtravel.entity.travel.TravelBean;
import com.mcy.mtravel.presenter.TravelDetialPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.TravelDetialView;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelDetialActivity extends MVPActivity<TravelDetialPresenter> implements TravelDetialView {

    @BindView(R.id.img_cover)
    ImageView mImgCover;
    @BindView(R.id.txt_name_en)
    TextView mTxtNameEn;
    @BindView(R.id.txt_name_ch)
    TextView mTxtNameCh;
    @BindView(R.id.lay_middle)
    LinearLayout mLayMiddle;
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
    private LinearLayoutManager mLayoutManager;
    private TravelDetialAdapter mAdapter;
    private List<AttractionContentsBean> mList;
    private int mHeadHeight;
    private int mTop;
    private int mBottom;
    private String tempTitle;

    @Override
    public void initVariables() {
        mID = getIntent().getStringExtra(FinalParams.TRIPS_DETIAL_ID);
        mList = new ArrayList<>();
        mAdapter = new TravelDetialAdapter(mContext, mList, R.layout.item_travel_detial);
        super.initVariables();
    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_tips_travel_detial);
        ButterKnife.bind(this);
        mCollapsingToolbar.setTitleEnabled(false);
        mAdapter.setEmptyView(mEmptyView);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
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

                int nextPostion = firstPosition == mList.size() - 1 ? firstPosition : firstPosition + 1;
                AttractionContentsBean bean = mList.get(nextPostion);
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
        AttractionContentsBean bean = mList.get(position);
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
        SnackBarUtils.ShortSnackbar(mCoorBg, msg, type);
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
    public TravelDetialPresenter create() {
        return new TravelDetialPresenter(mID);
    }

    @Override
    public void getData(TravelBean headTravelBean, List<AttractionContentsBean> list, List<HotelsBean> hotels, List<AttractionsBean> attractions) {
        int width = DeviceUtils.getDeviceScreenWidth(mContext);
        mToolbar.setTitle(headTravelBean.getName_zh_cn());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mImgCover.setLayoutParams(new RelativeLayout.LayoutParams(width, (int) (width / 1.7)));
        Glide.with(mContext)
                .load(headTravelBean.getImage_url())
                .placeholder(R.drawable.weit_place)
                .into(mImgCover);
        mTxtNameEn.setText(headTravelBean.getName_en());
        mTxtNameCh.setText(headTravelBean.getName_zh_cn());
        mTxtDesc.setText(headTravelBean.getDescription());
        mLayDateHead.setText("实用贴士");

        mAdapter.setAttractionsBeans(attractions);
        mAdapter.setHotelsBeens(hotels);
        mAdapter.flushData(list);
    }
}
