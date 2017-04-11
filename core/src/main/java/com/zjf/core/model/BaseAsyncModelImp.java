package com.zjf.core.model;


import com.zjf.core.impl.OnAsyncModelListener;

/**
 * @author :ZJF
 * @version : 2016-12-05 下午 4:12
 */

public interface BaseAsyncModelImp<T> extends BaseModelImp<T>{

    void getData(OnAsyncModelListener<T> listener);
}
