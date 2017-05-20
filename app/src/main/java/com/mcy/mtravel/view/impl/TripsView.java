package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.index.CBannerBean;
import com.mcy.mtravel.entity.index.TripsBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by machengyuan on 2017/4/14.
 */

public interface TripsView extends BaseViewImp {

    void onRefreshData(List<TripsBean> data, List<CBannerBean> headData);

    void onGetMoreData(List<TripsBean> data);

}
