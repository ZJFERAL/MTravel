package com.mcy.mtravel.presenter;

import com.mcy.mtravel.model.GetTokenModel;
import com.mcy.mtravel.model.impl.GetTokenModelImpl;
import com.mcy.mtravel.view.impl.SplashView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;
import com.zjf.core.utils.LogUtils;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public class SplashPresenter extends Presenter<SplashView> {

    private GetTokenModelImpl mModel;
    private int loopTime = 0;

    public SplashPresenter() {
        mModel = new GetTokenModel();
    }

    @Override
    protected void onViewStart() {
        mModel.getData(new OnAsyncModelListener<String>() {
            @Override
            public void onFailure(String msg, int type) {
                loopTime++;
                if (loopTime < 3) {
                    onViewStart();
                } else {
                    mView.NetWorkDone("");
                    LogUtils.e("SPres_M_getData", msg);
                }
            }

            @Override
            public void onSuccess(String msg) {
                mView.NetWorkDone(msg);
            }
        });
    }
}
