package com.mcy.mtravel.model.impl;

import com.mcy.mtravel.entity.UserWithTripsBean;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseAsyncModelImp;

/**
 * Created by zhaojifeng on 2017/4/21.
 */

public interface UserInfoModelImpl extends BaseAsyncModelImp<UserWithTripsBean> {

    void onRefreshData(OnAsyncModelListener<UserWithTripsBean> listener);

}
