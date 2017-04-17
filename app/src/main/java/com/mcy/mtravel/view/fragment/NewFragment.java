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
import com.mcy.mtravel.adapter.NewsAdapter;
import com.mcy.mtravel.base.MVPFragment;
import com.mcy.mtravel.entity.IndexBean;
import com.mcy.mtravel.presenter.NewsPresenter;
import com.mcy.mtravel.view.activity.WebViewActivity;
import com.mcy.mtravel.view.impl.NewsView;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.SnackBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewFragment extends MVPFragment<NewsPresenter> implements NewsView {


    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBG;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.refreshview)
    SwipeRefreshLayout mRefreshview;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    Unbinder unbinder;

    private int lastPosition = 0;
    private NewsAdapter mAdapter;
    private List<IndexBean.DataBean.FeedsBean.ListBean> mListBeen;

    public NewFragment() {
        // Required empty public constructor
    }

    @Override
    public void initVariables() {
        super.initVariables();
        mListBeen = new ArrayList<>();
        mAdapter = new NewsAdapter(getContext(), mListBeen, R.layout.item_news);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
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
                mPresenter.onflushData();
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
                if (mAdapter.isLoading()) {
                    return;
                }
                String url = mAdapter.getData().get(position).getPage_url();
                Bundle bundle = new Bundle();
                bundle.putString("path", url);
                LogUtils.e("Path", url);
                jumpTo(getActivity(), WebViewActivity.class, bundle, false);
            }
        }));
    }


    @Override
    public void onStart() {
        super.onStart();
        mRecyclerview.smoothScrollToPosition(lastPosition);
    }

    @Override
    public void onRefreshData(List<IndexBean.DataBean.FeedsBean.ListBean> data, View headView) {
        mAdapter.flushData(data);
        onCloseSwipe();
    }

    @Override
    public void onGetMoreData(List<IndexBean.DataBean.FeedsBean.ListBean> data) {
        mAdapter.addNewData(data);
        onCloseSwipe();
    }

    public void onCloseSwipe() {
        if (mRefreshview != null && mRefreshview.isRefreshing()) {
            mRefreshview.setRefreshing(false);
        }
        if (mAdapter != null) {
            mAdapter.onCompleteLoading();
        }
    }

    @Override
    public void onFailure(String msg, int type) {

    }


    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBG, msg, type).show();
    }

    @Override
    public NewsPresenter create() {
        return new NewsPresenter();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
