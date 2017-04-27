package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.StrategyDetialBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/27.
 */

public interface TipView extends BaseViewImp {
    void onGetData(List<StrategyDetialBean> list);
}
