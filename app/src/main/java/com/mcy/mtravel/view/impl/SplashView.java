package com.mcy.mtravel.view.impl;

import com.zjf.core.view.BaseViewImp;

/**
 * Created by machengyuan on 2017/4/14.
 */

public interface SplashView extends BaseViewImp {


    void NetWorkDone(String token);

    void exit();

    void showPermisssionDialog(String permissionName, String tips);

}
