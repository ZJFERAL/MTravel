package com.mcy.mtravel.view.impl;

import com.mcy.mtravel.entity.NotesBean;
import com.mcy.mtravel.entity.TripsBean;
import com.zjf.core.view.BaseViewImp;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/20.
 */

public interface TripsNoteView extends BaseViewImp {

    void onFailure(String msg, int type);

    void onLeftView(List<String> dates, List<List<String>> items);

    void onRecyclerViewData(List<NotesBean> notesBeanList);

    void onTitleData(TripsBean bean);
}
