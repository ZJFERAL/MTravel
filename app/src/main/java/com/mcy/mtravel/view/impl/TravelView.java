package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.TravelListBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public interface TravelView extends BaseViewImp {
    void onRefreshData(List<TravelListBean> data);

    void onGetMoreData(List<TravelListBean> data);
}