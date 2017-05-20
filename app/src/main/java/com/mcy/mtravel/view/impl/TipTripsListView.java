package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.tip.TipTripsBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by machengyuan on 2017/5/3.
 */

public interface TipTripsListView extends BaseViewImp {

    void onRefreshData(List<TipTripsBean> data);

    void onGetMoreData(List<TipTripsBean> data);
}
