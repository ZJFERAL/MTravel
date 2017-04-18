package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.CBannerBean;
import com.mcy.mtravel.entity.TripsBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public interface TripsView extends BaseViewImp {

    void onRefreshData(List<TripsBean> data, List<CBannerBean> headData);

    void onGetMoreData(List<TripsBean> data);

    void onFailure(String msg, int type);
}
