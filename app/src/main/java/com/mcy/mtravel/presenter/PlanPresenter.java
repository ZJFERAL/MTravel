package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.TargetPlaceBean;
import com.mcy.mtravel.model.PlanModel;
import com.mcy.mtravel.model.impl.PlanModelImpl;
import com.mcy.mtravel.view.impl.PlanView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;
import com.zjf.core.utils.LogUtils;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public class PlanPresenter extends Presenter<PlanView> {

    private PlanModelImpl mPlanModel;

    public PlanPresenter() {
        mPlanModel = new PlanModel();
    }

    @Override
    protected void onViewCreated() {
        refreshData();
    }

    public void refreshData() {
        mPlanModel.getData(new OnAsyncModelListener<List<TargetPlaceBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
                LogUtils.e("PlanPresenter", "refreshData error");
            }

            @Override
            public void onSuccess(List<TargetPlaceBean> msg) {
                mView.onRefreshData(msg);
            }
        });
    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        mPlanModel.cancel();
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mPlanModel = null;
    }
}
