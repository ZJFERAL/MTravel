package com.zjf.core.model;

import com.zjf.core.impl.OnAsyncModelListener;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public interface BaseModelImpl_Temp<T> {

    void cancel();

    void getMoreData(OnAsyncModelListener<T> listener);

    void refreshData(OnAsyncModelListener<T> listener);
}
