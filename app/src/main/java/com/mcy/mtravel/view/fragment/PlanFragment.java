package com.mcy.mtravel.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.MVPFragment;
import com.mcy.mtravel.presenter.PlanPresenter;
import com.mcy.mtravel.view.impl.PlanView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends MVPFragment<PlanPresenter> implements PlanView {


    public PlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void showSnakBar(String msg, int type) {

    }

    @Override
    public PlanPresenter create() {
        return null;
    }
}
