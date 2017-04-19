package com.mcy.mtravel.view.fragment;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.PlanZoneAdapter;
import com.mcy.mtravel.base.MVPFragment;
import com.mcy.mtravel.entity.TargetPlaceBean;
import com.mcy.mtravel.presenter.PlanPresenter;
import com.mcy.mtravel.view.impl.PlanView;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends MVPFragment<PlanPresenter> implements PlanView {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.refreshview)
    SwipeRefreshLayout mRefreshview;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;
    Unbinder unbinder;


    private List<TargetPlaceBean> mTargetBeanList;
    private PlanZoneAdapter mAdapter;

    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public void initVariables() {
        super.initVariables();
        mTargetBeanList = new ArrayList<>();
        mAdapter = new PlanZoneAdapter(getContext(), mTargetBeanList, R.layout.item_plan_zone);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
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
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBg, msg, type);
    }

    @Override
    public PlanPresenter create() {
        return new PlanPresenter();
    }


    @Override
    public void onRefreshData(List<TargetPlaceBean> data) {
        mAdapter.flushData(data);
        onCloseSwipe();
    }

    @Override
    public void onFailure(String msg, int type) {
        showSnakBar(msg, type);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
