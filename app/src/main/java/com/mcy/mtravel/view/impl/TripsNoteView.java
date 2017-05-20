package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.trips.NotesBean;
import com.mcy.mtravel.entity.index.TripsBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by machengyuan on 2017/4/20.
 */

public interface TripsNoteView extends BaseViewImp {


    void onLeftView(List<String> dates, List<List<String>> items);

    void onRecyclerViewData(List<NotesBean> notesBeanList);

    void onTitleData(TripsBean bean);
}
