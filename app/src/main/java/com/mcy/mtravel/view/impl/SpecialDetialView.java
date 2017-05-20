package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.special.ArticleSectionsBean;
import com.mcy.mtravel.entity.special.AttractionBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-05-20 下午 3:31
 */

public interface SpecialDetialView extends BaseViewImp {

    void getData(List<ArticleSectionsBean> mArticleSectionsBeen, List<AttractionBean> mAttractionBeen);
}
