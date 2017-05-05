package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.tipwiki.StrategyDetialBean;
import com.mcy.mtravel.model.TipModel;
import com.mcy.mtravel.view.impl.TipView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/27.
 */

public class TipPresenter extends Presenter<TipView> {

    private String mID;
    private TipModel mModelImp;

    public TipPresenter(String id) {
        mID = id;
        mModelImp = TipModel.getInstance(mID);
    }

    @Override
    protected void onViewCreated() {
        mModelImp.getData(new OnAsyncModelListener<List<StrategyDetialBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<StrategyDetialBean> msg) {
                mView.onGetData(msg);
            }
        });
    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        if (mModelImp != null) {
            mModelImp.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mModelImp = null;
    }
}
