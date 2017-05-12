package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.tiptrips.PlanNodesBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/5.
 */

public interface TripsDetialView extends BaseViewImp {

    void setMenu(List<String> titles, List<List<String>> items);

    void setData(List<PlanNodesBean> beanList);

}
