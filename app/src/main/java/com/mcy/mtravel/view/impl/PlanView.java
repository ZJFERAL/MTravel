package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.TargetPlaceBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public interface PlanView extends BaseViewImp{

    void onRefreshData(List<TargetPlaceBean> data);


    void onFailure(String msg, int type);
}
