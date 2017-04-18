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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
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

    private ViewFlipper mFlipper;
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
        mAdapter.flushData(data);
        if (!mAdapter.hasHeaderView()) {
            makeHead(headData);
        }
        onCloseSwipe();
    }

    private void makeHead(List<IndexBean.DataBean.SlideBean> headData) {
        mFlipper = new ViewFlipper(getContext());
        mFlipper.setLayoutParams(new RecyclerView.LayoutParams(DeviceUtils.getDeviceScreenWidth(getContext()),
                DeviceUtils.getDeviceScreenWidth(getContext()) / 2));
        for (int i = 0; i < headData.size(); i++) {
            final IndexBean.DataBean.SlideBean bean = headData.get(i);
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new RecyclerView.LayoutParams(DeviceUtils.getDeviceScreenWidth(getContext()),
                    DeviceUtils.getDeviceScreenWidth(getContext()) / 2));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String substring = bean.getUrl().substring(21, 26);
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
            Glide.with(getContext()).load(bean.getImage()).placeholder(R.drawable.weit_place).into(imageView);
            mFlipper.addView(imageView);
        }
        mFlipper.setInAnimation(getContext(), R.anim.fliper_enter);
        mFlipper.setOutAnimation(getContext(), R.anim.fliper_exit);
        mFlipper.setAutoStart(true);
        mAdapter.setHeaderView(mFlipper);
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
