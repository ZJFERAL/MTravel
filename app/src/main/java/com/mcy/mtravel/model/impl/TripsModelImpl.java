package com.mcy.mtravel.model.impl;

import com.mcy.mtravel.entity.index.CBannerBean;
import com.mcy.mtravel.entity.index.TripsBean;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseAsyncModelImp;

import java.util.List;

/**
 * Created by machengyuan on 2017/4/18.
 */

public interface TripsModelImpl extends BaseAsyncModelImp<List<TripsBean>> {

    void getRefreshData(OnAsyncModelListener<List<TripsBean>> listener);

    void getBanner(OnAsyncModelListener<List<CBannerBean>> listener);
}
