package com.mcy.mtravel.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.MVPFragment;
import com.mcy.mtravel.presenter.NewsPresenter;
import com.mcy.mtravel.view.impl.NewsView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends MVPFragment<NewsPresenter> implements NewsView {


    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void loaderData() {
        super.loaderData();
    }

    @Override
    public void showSnakBar(String msg, int type) {

    }

    @Override
    public NewsPresenter create() {
        return new NewsPresenter();
    }
}
