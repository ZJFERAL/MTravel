package com.mcy.mtravel.model;

import com.mcy.mtravel.entity.IndexBean;
import com.mcy.mtravel.model.impl.NewsModelImpl;
import com.zjf.core.impl.OnAsyncModelListener;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public class NewsModel implements NewsModelImpl {


    @Override
    public void getData(OnAsyncModelListener<IndexBean> listener) {

    }

    @Override
    public void cancel() {

    }
}
