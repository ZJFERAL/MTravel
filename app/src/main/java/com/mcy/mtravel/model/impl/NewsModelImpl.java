package com.mcy.mtravel.model.impl;

import com.mcy.mtravel.entity.IndexBean;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseAsyncModelImp;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public interface NewsModelImpl extends BaseAsyncModelImp<IndexBean> {
    void getRefreshData(OnAsyncModelListener<IndexBean> listener);
}
