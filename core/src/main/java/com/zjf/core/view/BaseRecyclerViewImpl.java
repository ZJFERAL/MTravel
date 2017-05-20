package com.zjf.core.view;

import java.util.List;

/**
 * Created by machengyuan on 2017/5/3.
 */

public interface BaseRecyclerViewImpl<B> extends BaseViewImp {

    void onRefreshData(List<B> data);

    void onGetMoreData(List<B> data);

    void onComplate();
}
