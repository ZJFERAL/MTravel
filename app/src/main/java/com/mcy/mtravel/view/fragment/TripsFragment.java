package com.mcy.mtravel.view.fragment;


import android.graphics.Color;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.mcy.mtravel.R;
import com.mcy.mtravel.adapter.TripsAdapter;
import com.mcy.mtravel.base.MVPFragment;
import com.mcy.mtravel.entity.CBannerBean;
import com.mcy.mtravel.entity.TripsBean;
import com.mcy.mtravel.presenter.TripsPresenter;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.activity.TripsNoteActivity;
import com.mcy.mtravel.view.impl.TripsView;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.SizeUtils;
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

    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBG;
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
                try {
                    int id = mAdapter.getData().get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putString(FinalParams.TRIPS_NOTE_ID, id + "");
                    jumpTo(getActivity(), TripsNoteActivity.class, bundle, false);
                } catch (Exception e) {
                }


            }
        }));
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBG, msg, type).show();
    }

    @Override
    public TripsPresenter create() {
        return new TripsPresenter();
    }

    @Override
    public void onRefreshData(List<TripsBean> data, List<CBannerBean> headData) {
        if (data != null && mAdapter != null) {
            mAdapter.flushData(data);
            if (!mAdapter.hasHeaderView() && headData != null) {
                makeHead(headData);
            }
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

    private void makeHead(List<CBannerBean> headData) {
        ViewFlipper headView = new ViewFlipper(getContext());
        headView.setLayoutParams(new RecyclerView.LayoutParams(DeviceUtils.getDeviceScreenWidth(getContext()),
                DeviceUtils.getDeviceScreenWidth(getContext()) / 2));

        for (int i = 1; i < headData.size(); i++) {
            CBannerBean bean = headData.get(i);

            RelativeLayout layout = new RelativeLayout(getContext());
            layout.setLayoutParams(new RecyclerView.LayoutParams(DeviceUtils.getDeviceScreenWidth(getContext()),
                    DeviceUtils.getDeviceScreenWidth(getContext()) / 2));


            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(DeviceUtils.getDeviceScreenWidth(getContext()),
                    DeviceUtils.getDeviceScreenWidth(getContext()) / 2));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(bean.getImage_url()).placeholder(R.drawable.weit_place).into(imageView);
            layout.addView(imageView);

            TextView txtNum = new TextView(getContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.setMargins(0, 0, SizeUtils.dp2px(5f, getContext()), SizeUtils.dp2px(5f, getContext()));
            txtNum.setBackgroundColor(getResources().getColor(R.color.colorTransDark));
            txtNum.setTextColor(Color.WHITE);
            txtNum.setLayoutParams(params);
            txtNum.setText(i + "");
            txtNum.setPadding(5, 5, 5, 5);
            layout.addView(txtNum);
            headView.addView(layout);
        }
        headView.setInAnimation(getContext(), R.anim.fliper_enter);
        headView.setOutAnimation(getContext(), R.anim.fliper_exit);
        headView.setAutoStart(true);
        mAdapter.setHeaderView(headView);
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
        mAdapter.removeHeaderView();
        mAdapter = null;
    }
}
