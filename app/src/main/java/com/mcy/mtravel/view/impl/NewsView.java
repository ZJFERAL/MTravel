package com.mcy.mtravel.view.impl;

import android.view.View;

import com.mcy.mtravel.entity.IndexBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public interface NewsView extends BaseViewImp {

    void onRefreshData(List<IndexBean.DataBean.FeedsBean.ListBean> data, View headView);

    void onGetMoreData(List<IndexBean.DataBean.FeedsBean.ListBean> data);

    void onFailure(String msg, int type);

}
