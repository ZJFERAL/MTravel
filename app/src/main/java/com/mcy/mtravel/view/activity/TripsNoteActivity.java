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
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
import com.zjf.core.utils.ImageUtils;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.SnackBarUtils;
import com.zjf.core.utils.TimeUtils;

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
    @BindView(R.id.txt_day_num)
    TextView mTxtDayNum;
    @BindView(R.id.txt_head_time)
    TextView mTxtTime;
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
    @BindView(R.id.float_action_menu)
    FloatingActionButton mFloatActionMenu;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.img_head_user)
    ImageView mImgHead;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.lay_date_head_line)
    LinearLayout mLayDateHead;
    @BindView(R.id.bg_head)
    LinearLayout mBgHead;
    @BindView(R.id.bg_menu)
    LinearLayout mBgMenu;


    private String mTripsID;
    private ExpandableAdapter mExpandableAdapter;
    private List<NotesBean> mNoteList;
    private TripsNoteAdapter mAdapter;
    private List<List<String>> mItems;
    private LinearLayoutManager mLayoutManager;
    private ViewGroup.LayoutParams mParams;
    private int mHeadHeight;
    private int mTop;
    private int mBottom;
    private boolean isScrollToFirst;
    private int mID;
    private List<String> mMenuTitles;


    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mTripsID = data.getString(FinalParams.TRIPS_NOTE_ID);
            LogUtils.e("mTripsID", mTripsID);
        }
        super.initVariables();
        mNoteList = new ArrayList<>();
        mAdapter = new TripsNoteAdapter(mContext, mNoteList, R.layout.item_trips_notes);

    }

    @Override
    public void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_trips_note);
        ButterKnife.bind(this);
        mAdapter.setEmptyView(mEmptyView);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        mDrawer.setScrimColor(Color.TRANSPARENT);
        mImgUser.setVisibility(View.GONE);
        mCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
    }

    @Override
    public void setListener() {
        mImgHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FinalParams.USER_ID, mID + "");
                jumpTo(TripsNoteActivity.this, UserInfoActivity.class, bundle, false);
            }
        });

        mFloatActionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDrawer.isDrawerOpen(Gravity.START)) {
                    mDrawer.openDrawer(Gravity.START);
                }
            }
        });
        mExpandListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String title = mItems.get(groupPosition).get(childPosition);
                int times = 0;
                for (int i = 0; i <= groupPosition; i++) {
                    List<String> strings = mItems.get(i);
                    int size = strings.size();
                    for (int j = 0; j < size; j++) {
                        if (strings.get(j).equals(title)) {
                            times++;
                        }
                        if (i == groupPosition && j == childPosition) {
                            break;
                        }
                    }
                }
                LogUtils.e(title + " " + times);
                int index = getTargetIndexByTitle(title, times);
                boolean isFirst = judgeFirst(index);
                moveToPosition(index, isFirst);
                mDrawer.closeDrawer(Gravity.START);
                return false;
            }
        });

        mExpandListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                String s = mMenuTitles.get(groupPosition);
                int index = getIndexByMenuTitle(s);
                moveToPosition(index, true);
                setTitleData(index);
                mDrawer.closeDrawer(Gravity.START);
                return true;
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


                int nextPostion = firstPosition == mNoteList.size() - 1 ? firstPosition : firstPosition + 1;
                NotesBean nextBean = mNoteList.get(nextPostion);
                int nextBeanDay = nextBean.getDay();

                if (mHeadHeight == 0) {
                    mHeadHeight = mLayDateHead.getHeight();
                    mTop = mLayDateHead.getTop();
                    mBottom = mLayDateHead.getBottom();
                }
                if (nextBeanDay != day) {
                    if (mLayoutManager.getChildCount() >= 2) {
                        int top = recyclerView.getChildAt(1).getTop();
                        if (mParams == null) {
                            mParams = mLayDateHead.getLayoutParams();
                        }
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

                if (move) {
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

                if (moveToUp) {
                    moveToUp = false;
                    if (!isScrollToFirst) {
                        mRecyclerview.scrollBy(0, -mHeadHeight);
                    }
                    isAutoScroll = false;
                }


                if (!isAutoScroll) {
                    if (dy > 0 && (!isActionBtnHide)) {
                        downActionButton();

                    } else if (dy < 0 && isActionBtnHide) {
                        upActionButon();
                    }
                }

            }
        });
    }

    private int getIndexByMenuTitle(String s) {
        int index = 0;
        int size = mNoteList.size();
        for (int i = 0; i < size - 1; i++) {
            NotesBean bean = mNoteList.get(i);
            int day = bean.getDay();
            if (s.equals(day + "")) {
                index = i;
                break;
            }
        }
        return index;
    }

    private boolean judgeFirst(int index) {
        if (index == 0) {
            return true;
        } else {
            return !(mNoteList.get(index - 1).getDay() == mNoteList.get(index).getDay());
        }

    }

    private int day = -1;

    private void setTitleData(int position) {
        NotesBean bean = mNoteList.get(position);
        int currentDay = bean.getDay();
        if (currentDay != this.day) {
            day = currentDay;
            String date = bean.getTrip_date();
            mTxtDayNum.setText("Day" + day);
            mTxtTime.setText(date + " " + TimeUtils.getWeek(date, "yyyy-MM-dd"));
        }
    }


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

    private int getTargetIndexByTitle(String title, int times) {
        int locTimes = 0;
        int index = 0;
        int size = mNoteList.size();
        for (int i = 0; i < size; i++) {
            NotesBean bean = mNoteList.get(i);
            String name = bean.getEntry_name();
            String lastName = "";
            int lastDay = -1;
            if (i != 0) {
                lastName = mNoteList.get(i - 1).getEntry_name();
                lastDay = mNoteList.get(i - 1).getDay();
            }
            int day = mNoteList.get(i).getDay();
            if (title.equals(name)) {
                LogUtils.e(name);
                if ((!title.equals(lastName))) {
                    locTimes++;
                    if (locTimes == times) {
                        index = i;
                        break;
                    }
                } else if (day != lastDay) {
                    locTimes++;
                    if (locTimes == times) {
                        index = i;
                        break;
                    }
                }

            }
        }
        return index;
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
        mFloatActionMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLeftView(List<String> dates, List<List<String>> items) {
        mMenuTitles = dates;
        mItems = items;
        mExpandableAdapter = new ExpandableAdapter(dates, items, mContext);
        mExpandListview.setAdapter(mExpandableAdapter);
        int size = dates.size();
        for (int i = 0; i < size; i++) {
            mExpandListview.expandGroup(i);
        }
    }

    @Override
    public void onRecyclerViewData(List<NotesBean> notesBeanList) {
        mNoteList.clear();
        mNoteList.addAll(notesBeanList);
        mAdapter.flushData(notesBeanList);

        NotesBean bean = mNoteList.get(0);
        int day = bean.getDay();
        String date = bean.getTrip_date();
        mTxtDayNum.setText("Day" + day);
        mTxtTime.setText(date + " " + TimeUtils.getWeek(date, "yyyy-MM-dd"));
        onCloseSwipe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if (!mDrawer.isDrawerOpen(Gravity.START)) {
                    onBackPressed();
                }
                break;
        }
        return false;
    }

    @Override
    public void onTitleData(TripsBean bean) {
        int width = DeviceUtils.getDeviceScreenWidth(mContext);
        Glide.with(mContext)
                .load(bean.getFront_cover_photo_url())
                .placeholder(R.drawable.weit_place)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable drawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                        mImgCover.setImageDrawable(drawable);
                        setTitleBackColor(drawable);
                        return true;
                    }
                }).into(mImgCover);
        mImgCover.setLayoutParams(new RelativeLayout.LayoutParams(width, (int) (width / 1.7)));

        Glide.with(mContext)
                .load(bean.getUser().getImage())
                .override(width / 8, width / 8)
                .placeholder(R.drawable.ic_insert_emoticon_orange_a200_48dp)
                .into(mImgHead);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 8, width / 8);
        mImgHead.setLayoutParams(params);
        mCollapsingToolbar.setTitle(bean.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mID = bean.getUser().getId();
    }

    private void setTitleBackColor(GlideDrawable drawable) {
        try {
            Palette.Builder builder = Palette.from(ImageUtils.drawableToBitmap(drawable));
            builder.generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch swatch = palette.getLightMutedSwatch();
                    if (swatch == null) {
                        LogUtils.e("swatch == null");
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
                        mCollapsingToolbar.setContentScrimColor(swatch.getRgb());
                        mCollapsingToolbar.setCollapsedTitleTextColor(swatch.getTitleTextColor());
                        mFloatActionMenu.setRippleColor(swatch.getRgb());
                    }
                    Palette.Swatch swatch_lm = palette.getLightMutedSwatch();
                    Palette.Swatch swatch_lv = palette.getLightVibrantSwatch();
                    if (swatch_lm != null && swatch_lv != null) {
                        mBgMenu.setBackgroundColor(swatch_lm.getRgb());
                        mBgHead.setBackgroundColor(swatch_lv.getRgb());
                    }


                }
            });
        } catch (Exception e) {
            LogUtils.e("setTitleBackColor", e.getMessage());
        } finally {
            onCloseSwipe();
        }
    }
}
