package com.mcy.mtravel.view.activity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.ExpandableAdapter;
import com.mcy.mtravel.adapter.TripsDetialAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.tiptrips.PlanNodesBean;
import com.mcy.mtravel.presenter.TripsDetialPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.TripsDetialView;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripsDetialActivity extends MVPActivity<TripsDetialPresenter> implements TripsDetialView {

    @BindView(R.id.img_cover)
    ImageView mImgCover;
    @BindView(R.id.txt_content)
    TextView mTxtContent;
    @BindView(R.id.txt_date)
    TextView mTxtDate;
    @BindView(R.id.txt_num)
    TextView mTxtNum;
    @BindView(R.id.txt_title)
    TextView mTxtTitle;
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
    @BindView(R.id.float_action_menu)
    FloatingActionButton mFloatActionMenu;
    @BindView(R.id.empty_view)
    FrameLayout mEmptyView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;
    @BindView(R.id.img_head_user)
    ImageView mImgHeadUser;
    @BindView(R.id.bg_head)
    LinearLayout mBgHead;
    @BindView(R.id.expand_listview)
    ExpandableListView mExpandListview;
    @BindView(R.id.bg_menu)
    LinearLayout mBgMenu;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    private String mID, mUrl, mTitle, mContent, mDayNum, mPlaceNum;
    private LinearLayoutManager mLayoutManager;
    private List<PlanNodesBean> mNodesBeen;
    private TripsDetialAdapter mAdapter;
    private List<List<String>> mItems;
    private List<String> mMenuTitles;
    private ExpandableAdapter mExpandableAdapter;

    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mID = data.getString(FinalParams.TRIPS_DETIAL_ID);
            mUrl = data.getString(FinalParams.TRIPS_DETIAL_IMG_URL);
            mTitle = data.getString(FinalParams.TRIPS_DETIAL_TITLE);
            mContent = data.getString(FinalParams.TRIPS_DETIAL_CONTENT);
            mDayNum = data.getString(FinalParams.TRIPS_DETIAL_DAY_NUM);
            mPlaceNum = data.getString(FinalParams.TRIPS_DETIAL_PLACE_NUM);
        }
        super.initVariables();
        mNodesBeen = new ArrayList<>();
        mAdapter = new TripsDetialAdapter(mContext, mNodesBeen, R.layout.item_trips_detial_normol, R.layout.item_trips_detial_candidate);
    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_tips_trips_detial);
        ButterKnife.bind(this);
        mImgHeadUser.setVisibility(View.GONE);
        mAdapter.setEmptyView(mEmptyView);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        mDrawer.setScrimColor(Color.TRANSPARENT);
        mExpandListview.setGroupIndicator(null);
        mCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
    }

    @Override
    public void setListener() {
        mFloatActionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDrawer.isDrawerOpen(Gravity.START)) {
                    mDrawer.openDrawer(Gravity.START);
                }
            }
        });

        mExpandListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String s = mMenuTitles.get(groupPosition);
                int index = findPositionByTitel(s);
                moveToPosition(index, true);
                setTitleData(index);
                mDrawer.closeDrawer(Gravity.START);
                return true;
            }
        });
        mExpandListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String s = mItems.get(groupPosition).get(childPosition);
                int index = findPositionByItems(s);
                moveToPosition(index, false);
                mDrawer.closeDrawer(Gravity.START);
                return false;
            }
        });
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
                int nextPostion = firstPosition == mNodesBeen.size() - 1 ? firstPosition : firstPosition + 1;
                PlanNodesBean nextBean = mNodesBeen.get(nextPostion);
                String title = nextBean.getFirstTitle();

                if (mHeadHeight == 0) {
                    mHeadHeight = mTxtDayNum.getHeight();
                    mTop = mTxtDayNum.getTop();
                    mBottom = mTxtDayNum.getBottom();
                }
                String currentTitle = mNodesBeen.get(firstPosition).getFirstTitle();
                if ((!currentTitle.equals(title)) && (!TextUtils.isEmpty(title))) {
                    if (mLayoutManager.getChildCount() >= 2) {
                        int top = recyclerView.getChildAt(1).getTop();
                        if (top <= mHeadHeight) {
                            mTxtDayNum.setTop(mTop - (mHeadHeight - top));
                            mTxtDayNum.setBottom(mBottom - (mHeadHeight - top));
                        } else {
                            mTxtDayNum.setTop(mTop);
                            mTxtDayNum.setBottom(mBottom);
                        }
                    }
                } else

                {
                    mTxtDayNum.setTop(mTop);
                    mTxtDayNum.setBottom(mBottom);
                }

                if (move)

                {
                    move = false;
                    //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                    int n = mIndex - firstPosition;
                    if (0 <= n && n < mRecyclerview.getChildCount()) {
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = mRecyclerview.getChildAt(n).getTop();
                        //最后的移动
                        if (!isScrollToFirst) {
                            top = top - mHeadHeight;
                        }
                        mRecyclerview.scrollBy(0, top);
                        isAutoScroll = false;
                    }
                }

                if (moveToUp)

                {
                    moveToUp = false;
                    if (!isScrollToFirst) {
                        mRecyclerview.scrollBy(0, -mHeadHeight);
                    }
                    isAutoScroll = false;
                }


                if (!isAutoScroll)

                {
                    if (dy > 0 && (!isActionBtnHide)) {
                        downActionButton();

                    } else if (dy < 0 && isActionBtnHide) {
                        upActionButon();
                    }
                }

            }
        });

    }

    private int findPositionByItems(String s) {
        int size = mNodesBeen.size();
        for (int i = 0; i < size; i++) {
            PlanNodesBean bean = mNodesBeen.get(i);
            if (bean.getEntry_name().equals(s)) {
                return i;
            }
        }
        return 0;
    }

    private void setTitleData(int index) {
        String title = mNodesBeen.get(index).getFirstTitle();
        if (!TextUtils.isEmpty(title)) {
            mTxtDayNum.setText(title);
        }
    }

    private int findPositionByTitel(String s) {
        int size = mNodesBeen.size();
        for (int i = 0; i < size; i++) {
            PlanNodesBean bean = mNodesBeen.get(i);
            if (s.equals(bean.getFirstTitle())) {
                return i;
            }
        }
        return 0;
    }

    private int mHeadHeight;
    private int mTop;
    private int mBottom;
    private boolean isScrollToFirst;
    private boolean isAutoScroll = false;
    private boolean move = false;
    private boolean moveToUp = false;

    private int mIndex;

    private void moveToPosition(int index, boolean isFirst) {
        isScrollToFirst = isFirst;
        mIndex = index;
        //获取当前recycleView屏幕可见的第一项和最后一项的Position
        int firstItem = mLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (index <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            mRecyclerview.scrollToPosition(index);
            moveToUp = true;
            isAutoScroll = true;
        } else if (index <= lastItem) {
            //当要置顶的项已经在屏幕上显示时，计算它离屏幕原点的距离
            int top = mRecyclerview.getChildAt(index - firstItem).getTop();
            if (!isFirst) {
                top = top - mHeadHeight;
            }
            mRecyclerview.smoothScrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            mRecyclerview.scrollToPosition(index);
            //记录当前需要在RecyclerView滚动监听里面继续第二次滚动
            move = true;
            isAutoScroll = true;
        }
    }


    private boolean isActionBtnHide = false;
    private boolean isAniming = false;

    private void upActionButon() {
        if (!isAniming) {
            mFloatActionMenu.setVisibility(View.VISIBLE);
            mFloatActionMenu.animate().alpha(1F).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAniming = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isAniming = false;
                    isActionBtnHide = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    isAniming = false;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }

    private void downActionButton() {
        if (!isAniming) {
            mFloatActionMenu.animate().alpha(0F).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAniming = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mFloatActionMenu.setVisibility(View.GONE);
                    isActionBtnHide = true;
                    isAniming = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    isAniming = false;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBg, msg, type).show();
    }

    @Override
    public void onFailure(String msg, int type) {
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
        mFloatActionMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public TripsDetialPresenter create() {
        return new TripsDetialPresenter(mID);
    }

    @Override
    public void setMenu(List<String> titles, List<List<String>> items) {
        mMenuTitles = titles;
        mItems = items;
        mExpandableAdapter = new ExpandableAdapter(titles, items, mContext);
        mExpandListview.setAdapter(mExpandableAdapter);
        int size = titles.size();
        for (int i = 0; i < size; i++) {
            mExpandListview.expandGroup(i);
        }
    }

    @Override
    public void setData(List<PlanNodesBean> beanList) {
        mNodesBeen.addAll(beanList);
        mAdapter.flushData(beanList);
        setTitel();
        onCloseSwipe();
    }

    private void setTitel() {
        int width = DeviceUtils.getDeviceScreenWidth(mContext);
        Glide.with(mContext)
                .load(mUrl)
                .placeholder(R.drawable.weit_place)
                .into(mImgCover);
        mImgCover.setLayoutParams(new RelativeLayout.LayoutParams(width, (int) (width / 1.7)));
        mTxtContent.setText(mContent);
        mTxtTitle.setText(mTitle);
        mToolbar.setTitle(mTitle);
        mCollapsingToolbar.setTitleEnabled(false);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTxtDate.setText(mDayNum + "天");
        mTxtNum.setText(mPlaceNum + "个旅行地");
        setTitleData(0);
    }

}
