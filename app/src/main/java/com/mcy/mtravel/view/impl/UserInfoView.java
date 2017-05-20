package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.user.UserWithTripsBean;
import com.zjf.core.view.BaseViewImp;

/**
 * Created by jifengZhao on 2017/4/21.
 */

public interface UserInfoView extends BaseViewImp {

    void onRefreshData(UserWithTripsBean data);

    void onGetMoreData(UserWithTripsBean data);

}
