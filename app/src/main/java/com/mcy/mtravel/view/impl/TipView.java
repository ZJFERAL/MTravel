package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.tipwiki.StrategyDetialBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by machengyuan on 2017/4/27.
 */

public interface TipView extends BaseViewImp {
    void onGetData(List<StrategyDetialBean> list);
}
