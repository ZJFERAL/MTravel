package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.index.TargetPlaceBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public interface PlanView extends BaseViewImp {

    void onLeftItem(List<String> data);

    void onRightItem(List<TargetPlaceBean.DestinationsBean> data);

}
