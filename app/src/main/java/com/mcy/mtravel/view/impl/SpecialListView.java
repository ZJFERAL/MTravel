package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.tip.SpecialListBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public interface SpecialListView extends BaseViewImp {
    void onRefreshData(List<SpecialListBean> data);

    void onGetMoreData(List<SpecialListBean> data);
}
