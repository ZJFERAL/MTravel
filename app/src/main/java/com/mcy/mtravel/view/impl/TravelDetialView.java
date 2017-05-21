package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.travel.AttractionContentsBean;
import com.mcy.mtravel.entity.travel.AttractionsBean;
import com.mcy.mtravel.entity.travel.HotelsBean;
import com.mcy.mtravel.entity.travel.TravelBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-05-21 上午 10:04
 */

public interface TravelDetialView extends BaseViewImp {

    void getData(TravelBean headTravelBean,
                 List<AttractionContentsBean> list,
                 List<HotelsBean> hotels,
                 List<AttractionsBean> attractions
    );
}
