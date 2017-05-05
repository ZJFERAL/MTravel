package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.tipwiki.ChildrenBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-04-30 下午 10:17
 */

public interface TipsDetialView extends BaseViewImp {
    void onGetData(List<ChildrenBean> list);
}
