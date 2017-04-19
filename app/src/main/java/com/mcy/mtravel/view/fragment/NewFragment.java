package com.mcy.mtravel.view.fragment;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.SnackBarUtils;
import com.zjf.core.widget.CircleIndicator;
import com.zjf.core.widget.LoopViewPager;

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

    private NewsAdapter mAdapter;
    private List<IndexBean.DataBean.FeedsBean.ListBean> mListBeen;
    private List<IndexBean.DataBean.SlideBean> mHeadData;

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
                mPresenter.onFlushData();
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
                String url = mAdapter.getData()
                        .get(position)
                        .getPage_url()
                        + "?client_id=qyer_guide_app_android&track_deviceid="
                        + DeviceUtils.getDeviceId(getContext())
                        + "&track_app_version=1.9.5&track_user_id=0&source=app";
                Bundle bundle = new Bundle();
                bundle.putString("path", url);
                jumpTo(getActivity(), WebViewActivity.class, bundle, false);
            }
        }));
    }


    @Override
    public void onRefreshData(List<IndexBean.DataBean.FeedsBean.ListBean> data, List<IndexBean.DataBean.SlideBean> headData) {
        mHeadData = headData;
        mAdapter.flushData(data);
        if (!mAdapter.hasHeaderView()) {
            makeHead();
        }
        onCloseSwipe();
    }

    private void makeHead() {
        LoopViewPager loopViewPager = new LoopViewPager(getContext());
        loopViewPager.setAutoStart();
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < mHeadData.size(); i++) {
            urls.add(mHeadData.get(i).getImage());
        }
        loopViewPager.setUrls(urls);
        loopViewPager.setPointLayout(CircleIndicator.Gravity.RIGHT);
        loopViewPager.setCurrentItem(1, false);
        mAdapter.setHeaderView(loopViewPager);
        loopViewPager.setOnItemClickListener(new LoopViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(ViewPager viewPager, View view, int position) {
                String substring = mHeadData.get(position).getUrl().substring(21, 26);
                String url = "http://m.qyer.com/guide/page/"
                        + substring
                        + "/?client_id=qyer_guide_app_android&track_deviceid="
                        + DeviceUtils.getDeviceId(getContext())
                        + "&track_app_version=1.9.5&track_user_id=0&source=app";
                Bundle bundle = new Bundle();
                bundle.putString("path", url);
                jumpTo(getActivity(), WebViewActivity.class, bundle, false);
            }
        });
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
        showSnakBar(msg, type);
        onCloseSwipe();
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
