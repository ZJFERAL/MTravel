package com.zjf.core.presenter;

/**
 * @author :ZJF
 * @version : 2016-12-16 下午 4:14
 */

public interface BasePresenter<V> {
    void onViewAttached(V view);
    void onViewDeached();
    void onDestroyed();
}
