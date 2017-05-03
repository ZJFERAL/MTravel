package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.TipTripsBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public interface TipTripsListView extends BaseViewImp {

    void onRefreshData(List<TipTripsBean> data);

    void onGetMoreData(List<TipTripsBean> data);
}
