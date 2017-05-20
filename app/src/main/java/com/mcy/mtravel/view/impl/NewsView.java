package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.index.IndexBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by machengyuan on 2017/4/14.
 */

public interface NewsView extends BaseViewImp {

    void onRefreshData(List<IndexBean.DataBean.FeedsBean.ListBean> data, List<IndexBean.DataBean.SlideBean> headData);

    void onGetMoreData(List<IndexBean.DataBean.FeedsBean.ListBean> data);

}
