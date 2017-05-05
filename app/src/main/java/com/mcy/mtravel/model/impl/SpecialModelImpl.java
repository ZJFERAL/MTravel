package com.mcy.mtravel.model.impl;

import com.mcy.mtravel.entity.tip.SpecialListBean;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseAsyncModelImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public interface SpecialModelImpl extends BaseAsyncModelImp<List<SpecialListBean>>{
    void onRefreshData(OnAsyncModelListener<List<SpecialListBean>> listener);
}
