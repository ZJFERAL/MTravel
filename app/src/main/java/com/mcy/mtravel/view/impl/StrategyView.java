package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.StrategyBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/26.
 */

public interface StrategyView extends BaseViewImp{

    void onRefreshData(List<StrategyBean> data);


    void onFailure(String msg, int type);
}
