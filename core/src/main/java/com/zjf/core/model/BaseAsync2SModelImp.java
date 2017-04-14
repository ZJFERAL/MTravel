package com.zjf.core.model;


import com.zjf.core.impl.OnAsyncModel2SListener;

/**
 * @author :ZJF
 * @version : 2016-12-05 下午 4:12
 */

public interface BaseAsync2SModelImp<T,V> extends BaseModelImpl{
    void getData(OnAsyncModel2SListener<T, V> listener);
}
