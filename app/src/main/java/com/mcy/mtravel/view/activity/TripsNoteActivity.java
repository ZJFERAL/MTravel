package com.mcy.mtravel.view.activity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @BindView(R.id.viewfliper_data)
    TextView mViewfliper;
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


    private String mTripsID;
    private ExpandableAdapter mExpandableAdapter;
    private List<NotesBean> mNoteList;
    private TripsNoteAdapter mAdapter;
    private List<List<String>> mItems;
    private LinearLayoutManager mLayoutManager;

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
        setContentView(R.layout.activity_trips_note);
        ButterKnife.bind(this);
        mAdapter.setEmptyView(mEmptyView);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);
        mDrawer.setScrimColor(Color.TRANSPARENT);
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
        mExpandListview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String title = mItems.get(groupPosition).get(childPosition);
                int index = getFirstIndexByTitle(title);
                moveToPosition(index);
                mDrawer.closeDrawer(Gravity.START);
                return false;
            }
        });

        mExpandListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                List<String> list = mItems.get(groupPosition);
                if (list == null || list.size() == 0) {
                    if (groupPosition == 0) {
                        mRecyclerview.scrollToPosition(0);
                    } else {
                        try {
                            List<String> strings = mItems.get(groupPosition - 1);
                            int size = strings.size();
                            String title = strings.get(size - 1);
                            int index = getLastIndexByTitle(title);
                            if (index != 0) {
                                moveToPosition(index + 1);
                            }
                            mDrawer.closeDrawer(Gravity.START);
                        } catch (Exception e) {
                            LogUtils.e("groupsClick", "error Index");
                        }
                    }
                }
                return false;
            }
        });

        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int day = -1;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = mLayoutManager.findFirstVisibleItemPosition();
                if (move) {
                    move = false;
                    //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                    int n = mIndex - position;
                    if (0 <= n && n < mRecyclerview.getChildCount()) {
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = mRecyclerview.getChildAt(n).getTop();
                        //最后的移动
                        mRecyclerview.scrollBy(0, top);
                        isAutoScroll = false;
                    }
                }

                if (!isAutoScroll) {
                    if (dy > 0 && (!isActionBtnHide)) {
                        downActionButton();

                    } else if (dy < 0 && isActionBtnHide) {
                        upActionButon();
                    }
                }
                NotesBean bean = mNoteList.get(position);
                int currentDay = bean.getDay();
                if (currentDay != this.day) {
                    day = currentDay;
                    String date = bean.getTrip_date();
                    mViewfliper.setText("Day" + day + " " + date + " " + TimeUtils.getWeek(date, "yyyy-MM-dd"));
                }

            }
        });
    }


    private boolean isAutoScroll = false;
    private boolean move = false;

    private int mIndex;

    private void moveToPosition(int index) {
        mIndex = index;
        //获取当前recycleView屏幕可见的第一项和最后一项的Position
        int firstItem = mLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (index <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            mRecyclerview.scrollToPosition(index);
        } else if (index <= lastItem) {
            //当要置顶的项已经在屏幕上显示时，计算它离屏幕原点的距离
            int top = mRecyclerview.getChildAt(index - firstItem).getTop();
            mRecyclerview.scrollBy(0, top);
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

    private int getLastIndexByTitle(String title) {
        int index = 0;
        int size = mNoteList.size();
        for (int i = 0; i < size - 1; i++) {
            NotesBean bean = mNoteList.get(i);
            String name = bean.getEntry_name();
            if (title.equals(name)) {
                NotesBean beanNext = mNoteList.get(i + 1);
                String nextName = beanNext.getEntry_name();
                if (TextUtils.isEmpty(nextName)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    private int getFirstIndexByTitle(String title) {
        int index = 0;
        int size = mNoteList.size();
        for (int i = 0; i < size; i++) {
            NotesBean bean = mNoteList.get(i);
            String name = bean.getEntry_name();
            if (title.equals(name)) {
                index = i;
                break;
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
    }

    @Override
    public void onLeftView(List<String> dates, List<List<String>> items) {
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
        mViewfliper.setText("Day" + day + " " + date + " " + TimeUtils.getWeek(date, "yyyy-MM-dd"));
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
                .into(mImgCover);
        mImgCover.setLayoutParams(new RelativeLayout.LayoutParams(width, width / 2));
        Glide.with(mContext)
                .load(bean.getUser().getImage())
                .placeholder(R.drawable.weit_place)
                .into(mImgUser);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width / 9, width / 9);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mImgUser.setLayoutParams(params);
        mCollapsingToolbar.setTitle(bean.getName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
