package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.tip.StrategyBean;
import com.mcy.mtravel.model.StrategyModel;
import com.mcy.mtravel.model.impl.StrategyModelImpl;
import com.mcy.mtravel.view.impl.StrategyView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/26.
 */

public class StrategyPresenter extends Presenter<StrategyView> {

    private String mStrategyID;
    private StrategyModelImpl mStrategyModel;

    public StrategyPresenter(String strategyID) {
        mStrategyID = strategyID;
        mStrategyModel = new StrategyModel(mStrategyID);
    }

    @Override
    protected void onViewCreated() {
        onGetData();
    }

    public void onGetData() {
        mStrategyModel.getData(new OnAsyncModelListener<List<StrategyBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<StrategyBean> msg) {
                mView.onRefreshData(msg);
            }
        });
    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        if (mStrategyModel != null) {
            mStrategyModel.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mStrategyModel = null;
    }
}
