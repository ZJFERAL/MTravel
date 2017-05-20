package com.mcy.mtravel.model.impl;

import com.mcy.mtravel.entity.tip.TipTripsBean;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseAsyncModelImp;

import java.util.List;

/**
 * Created by machengyuan on 2017/5/3.
 */

public interface TipTripsListModelImpl extends BaseAsyncModelImp<List<TipTripsBean>> {

    void onRefreshData(OnAsyncModelListener<List<TipTripsBean>> listener);

}
