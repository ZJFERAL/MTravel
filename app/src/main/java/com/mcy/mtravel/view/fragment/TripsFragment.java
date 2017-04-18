package com.mcy.mtravel.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.TripsAdapter;
import com.mcy.mtravel.base.MVPFragment;
import com.mcy.mtravel.entity.CBannerBean;
import com.mcy.mtravel.entity.TripsBean;
import com.mcy.mtravel.presenter.TripsPresenter;
import com.mcy.mtravel.view.impl.TripsView;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripsFragment extends MVPFragment<TripsPresenter> implements TripsView {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.refreshview)
    SwipeRefreshLayout mRefreshview;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    Unbinder unbinder;

    private TripsAdapter mAdapter;
    private List<TripsBean> mBeanList;

    public TripsFragment() {
        // Required empty public constructor
    }

    @Override
    public void initVariables() {
        super.initVariables();
        mBeanList = new ArrayList<>();
        mAdapter = new TripsAdapter(getContext(), mBeanList, R.layout.item_trips);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRefreshview.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerview.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void initListener() {
        mRefreshview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshData();
            }
        });

        mRecyclerview.addOnScrollListener(new CRecyclerViewAdapter.LLM_RefreshListenter() {
            @Override
            protected void loadMore() {
                mPresenter.getMoreData();
            }
        });

        mRecyclerview.addOnItemTouchListener(new CRecyclerViewAdapter.RecyclerItemClickListener(getContext(), new CRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mAdapter.isLoading() || (position == 0 && mAdapter.hasHeaderView())) {
                    return;
                }
                if (mAdapter.hasHeaderView()) {
                    position -= 1;
                }

            }
        }));
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(null, msg, type).show();
    }

    @Override
    public TripsPresenter create() {
        return new TripsPresenter();
    }

    @Override
    public void onRefreshData(List<TripsBean> data, List<CBannerBean> headData) {
        mAdapter.flushData(data);
        if (!mAdapter.hasHeaderView()) {
            makeHead(headData);
        }
        onCloseSwipe();
    }

    private void onCloseSwipe() {
        if (mRefreshview != null && mRefreshview.isRefreshing()) {
            mRefreshview.setRefreshing(false);
        }
        if (mAdapter != null) {
            mAdapter.onCompleteLoading();
        }
    }

    private void makeHead(List<CBannerBean> data) {

    }

    @Override
    public void onGetMoreData(List<TripsBean> data) {
        mAdapter.addNewData(data);
        onCloseSwipe();
    }

    @Override
    public void onFailure(String msg, int type) {
        showSnakBar(msg, type);
        onCloseSwipe();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
