package com.mcy.mtravel.view.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.PlanCountryAdapter;
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


    @BindView(R.id.radiogroup)
    RadioGroup mGroup;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBg;
    Unbinder unbinder;

    private PlanCountryAdapter mAdapter;
    private List<TargetPlaceBean.DestinationsBean> mList;

    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public void initVariables() {
        super.initVariables();
        mList = new ArrayList<>();
        mAdapter = new PlanCountryAdapter(getContext(), mList, R.layout.item_plan_country);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void initListener() {
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    View view = group.getChildAt(i);
                    if (view instanceof RadioButton) {
                        RadioButton radioButton = (RadioButton) view;
                        if (radioButton.isChecked()) {
                            mPresenter.getRightData(i);
                        }
                    }
                }
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
    public void onLeftItem(List<String> data) {
        int size = data.size();
        for (int i = 0; i < size; i++) {
            RadioButton rb = (RadioButton) LayoutInflater.from(getContext())
                    .inflate(R.layout.item_zone, mGroup, false);
            rb.setText(data.get(i));
            mGroup.addView(rb, i);
        }
        ((RadioButton) mGroup.getChildAt(0)).setChecked(true);
    }

    @Override
    public void onRightItem(List<TargetPlaceBean.DestinationsBean> data) {
        mAdapter.flushData(data);
        onCloseSwipe();
    }

    @Override
    public void onFailure(String msg, int type) {
        try {
            showSnakBar(msg, type);
        } catch (Exception e) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
        onCloseSwipe();
    }

    private void onCloseSwipe() {
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
