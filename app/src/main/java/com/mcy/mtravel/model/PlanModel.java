package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.TargetPlaceBean;
import com.mcy.mtravel.model.impl.PlanModelImpl;
import com.mcy.mtravel.utils.FinalParams;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhaojifeng on 2017/4/19.
 */

public class PlanModel implements PlanModelImpl {


    private List<Disposable> mDisposables;
    private CyjUrl mUrl;

    public PlanModel() {
        mDisposables = new ArrayList<>();
    }

    @Override
    public void cancel() {
        if (mDisposables != null) {
            if (mDisposables.size() > 0) {
                for (int i = 0; i < mDisposables.size(); i++) {
                    Disposable disposable = mDisposables.get(i);
                    if (disposable.isDisposed()) {
                        disposable.dispose();
                    }
                }
            }
            mDisposables.clear();
        }
    }

    @Override
    public void getData(final OnAsyncModelListener<List<TargetPlaceBean>> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null).create(CyjUrl.class);
        }
        mUrl.getTargetZone()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<TargetPlaceBean>>() {
                    @Override
                    public void accept(List<TargetPlaceBean> been) throws Exception {
                        if (been != null && been.size() > 0) {
                            listener.onSuccess(been);
                        } else {
                            listener.onFailure(App.getStringRes(R.string.error_net), FinalParams.ERROR_INFO);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onFailure(App.getStringRes(R.string.error_net), FinalParams.ERROR_INFO);
                    }
                });

    }
}
