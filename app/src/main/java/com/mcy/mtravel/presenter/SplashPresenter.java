package com.mcy.mtravel.presenter;

import android.Manifest;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.model.GetTokenModel;
import com.mcy.mtravel.model.impl.GetTokenModelImpl;
import com.mcy.mtravel.view.impl.SplashView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;
import com.zjf.core.utils.FinalParams;
import com.zjf.core.utils.LogUtils;

import io.reactivex.functions.Consumer;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public class SplashPresenter extends Presenter<SplashView> {

    private GetTokenModelImpl mModel;
    private int loopTime = 0;
    private RxPermissions mPermissions;

    public SplashPresenter(RxPermissions rxPermissions) {
        mPermissions = rxPermissions;
        mModel = new GetTokenModel();
    }

    @Override
    protected void onViewCreated() {
        requestPermission(Manifest.permission.READ_PHONE_STATE);
    }

    public void requestPermission(final String permissionName) {
        mPermissions.requestEach(permissionName)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (isAttached) {
                            if (permission.granted) {
                                switch (permission.name) {
                                    case Manifest.permission.READ_PHONE_STATE:
                                        getData();
                                        break;
                                }
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                requestPermission(permissionName);
                            } else {
                                mView.showPermisssionDialog(permissionName, App.getInstance().getString(R.string.permission));
                            }
                        }
                    }
                });
    }

    private void getData() {
        mModel.getData(new OnAsyncModelListener<String>() {
            @Override
            public void onFailure(String msg, int type) {
                if (type == FinalParams.ERROR_INFO) {
                    loopTime++;
                    if (loopTime < 2) {
                        onViewCreated();
                    } else {
                        mView.NetWorkDone("");
                        LogUtils.e("SPres_M_getData", msg);
                    }
                } else {
                    mView.showSnakBar(msg, type);
                    mView.NetWorkDone("");
                }

            }

            @Override
            public void onSuccess(String msg) {
                mView.NetWorkDone(msg);
            }
        });
    }


    @Override
    public void onViewDeached() {
        super.onViewDeached();
        if (mModel != null) {
            mModel.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mModel = null;
    }
}
