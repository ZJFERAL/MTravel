package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.TipTripsListAdapter;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.entity.tip.TipTripsBean;
import com.mcy.mtravel.presenter.TipTripsListPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.TipTripsListView;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipsTripsListActivity extends MVPActivity<TipTripsListPresenter> implements TipTripsListView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;
    @BindView(R.id.refreshview)
    SwipeRefreshLayout mRefreshview;

    private String mID;
    private String mTitle;
    private List<TipTripsBean> mList;
    private TipTripsListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    public void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            mID = data.getString(FinalParams.TIP_ID);
            mTitle = data.getString(FinalParams.TIP_TITLE);
            mList = new ArrayList<>();
            mAdapter = new TipTripsListAdapter(mContext, mList, R.layout.item_tip_trips_list);
        } else {
            showToast(getString(R.string.error_place));
        }
        super.initVariables();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_tips_trips_list);
        ButterKnife.bind(this);
        mToolbar.setTitle(mTitle + getString(R.string.trip));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRefreshview.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));
        mAdapter.setEmptyView(mEmptyView);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mLayoutManager);
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
        mRecyclerview.addOnItemTouchListener(new CRecyclerViewAdapter.RecyclerItemClickListener(mContext, new CRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TipTripsBean bean = mAdapter.getData().get(position);
                int id = bean.getId();
                Bundle bundle = new Bundle();
                bundle.putString(FinalParams.TRIPS_DETIAL_ID, id + "");
                bundle.putString(FinalParams.TRIPS_DETIAL_IMG_URL, bean.getImage_url());
                bundle.putString(FinalParams.TRIPS_DETIAL_TITLE, bean.getName());
                bundle.putString(FinalParams.TRIPS_DETIAL_CONTENT, bean.getDescription());
                bundle.putString(FinalParams.TRIPS_DETIAL_DAY_NUM, bean.getPlan_days_count() + "");
                bundle.putString(FinalParams.TRIPS_DETIAL_PLACE_NUM, bean.getPlan_nodes_count() + "");
                jumpTo(TipsTripsListActivity.this, TripsDetialActivity.class, bundle, false);
            }
        }));
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBg, msg, type);
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
    }

    @Override
    public TipTripsListPresenter create() {
        return new TipTripsListPresenter(mID);
    }

    @Override
    public void onRefreshData(List<TipTripsBean> data) {
        mAdapter.flushData(data);
        onCloseSwipe();
    }

    @Override
    public void onGetMoreData(List<TipTripsBean> data) {
        mAdapter.addNewData(data);
        onCloseSwipe();
    }

}
