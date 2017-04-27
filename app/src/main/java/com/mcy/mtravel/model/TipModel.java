package com.mcy.mtravel.model;

import com.mcy.mtravel.entity.StrategyDetialBean;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseAsyncModelImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/27.
 */

public class TipModel implements BaseAsyncModelImp<List<StrategyDetialBean>> {

    private static TipModel mTipModel;
    private static String mID;
    private List<StrategyDetialBean> mDetialBeen;

    private TipModel() {
        mDetialBeen = new ArrayList<>();
    }

    public TipModel getInstance(String tipID) {
        if (!tipID.equals(mID)) {
            mID = tipID;
            mDetialBeen.clear();
        }
        if (mTipModel == null) {
            synchronized (TipModel.class) {
                if (mTipModel == null) {
                    mTipModel = new TipModel();
                }
            }
        }
        return mTipModel;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void getData(OnAsyncModelListener<List<StrategyDetialBean>> listener) {

    }
}
