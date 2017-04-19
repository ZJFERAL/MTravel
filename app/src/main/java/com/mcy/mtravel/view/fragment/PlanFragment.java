package com.mcy.mtravel.view.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.MVPFragment;
import com.mcy.mtravel.entity.TargetPlaceBean;
import com.mcy.mtravel.presenter.PlanPresenter;
import com.mcy.mtravel.view.impl.PlanView;
import com.zjf.core.utils.SnackBarUtils;

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


    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public void initVariables() {
        super.initVariables();
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initListener() {
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

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

    private String getStrByInt(int anInt) {
        String string = "";
        switch (anInt) {
            case 1:
                string = getString(R.string.asian);
                break;
            case 2:
                string = getString(R.string.europe);
                break;
            case 3:
                string = getString(R.string.us);
                break;
            case 99:
                string = getString(R.string.in_gat);
                break;
            case 999:
                string = getString(R.string.in_dl);
                break;
        }
        return string;
    }

    @Override
    public void onLeftItem(List<String> data) {
        int size = data.size();
        for (int i = 0; i < size; i++) {
            RadioButton rb = (RadioButton) LayoutInflater.from(getContext())
                    .inflate(R.layout.item_zone, mGroup, false);
            int anInt = Integer.parseInt(data.get(i));
            String strByInt = getStrByInt(anInt);
            rb.setText(strByInt);
            mGroup.addView(rb);
        }
        mGroup.getChildAt(0).setSelected(true);
    }

    @Override
    public void onRightItem(List<TargetPlaceBean.DestinationsBean> data) {

    }

    @Override
    public void onFailure(String msg, int type) {
        showSnakBar(msg, type);
        onCloseSwipe();
    }

    private void onCloseSwipe() {
        if (mEmptyView.getVisibility() == View.VISIBLE) {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
