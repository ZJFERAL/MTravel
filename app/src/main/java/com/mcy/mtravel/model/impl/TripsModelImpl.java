package com.mcy.mtravel.model.impl;

import com.mcy.mtravel.entity.CBannerBean;
import com.mcy.mtravel.entity.TripsBean;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseAsyncModelImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/18.
 */

public interface TripsModelImpl extends BaseAsyncModelImp<List<TripsBean>> {

    void getRefreshData(OnAsyncModelListener<List<TripsBean>> listener);

    void getBanner(OnAsyncModelListener<List<CBannerBean>> listener);
}
