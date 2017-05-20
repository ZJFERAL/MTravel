package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.index.CBannerBean;
import com.mcy.mtravel.entity.index.TripsBean;
import com.mcy.mtravel.model.impl.TripsModelImpl;
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
 * Created by machengyuan on 2017/4/18.
 */

public class TripsModel implements TripsModelImpl {

    private int currentIndex = 1;
    private List<Disposable> mDisposables;
    private CyjUrl mUrl;

    public TripsModel() {
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
    public void getData(final OnAsyncModelListener<List<TripsBean>> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null,App.getInstance()).create(CyjUrl.class);
        }
        Disposable subscribe = mUrl.getTrips(currentIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<TripsBean>>() {
                    @Override
                    public void accept(List<TripsBean> tripsBeanList) throws Exception {
                        if (tripsBeanList != null && tripsBeanList.size() > 0) {
                            currentIndex++;
                            listener.onSuccess(tripsBeanList);
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
        mDisposables.add(subscribe);
    }

    @Override
    public void getRefreshData(OnAsyncModelListener<List<TripsBean>> listener) {
        currentIndex = 1;
        getData(listener);
    }

    @Override
    public void getBanner(final OnAsyncModelListener<List<CBannerBean>> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null).create(CyjUrl.class);
        }
        Disposable subscribe = mUrl.getCBanner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<CBannerBean>>() {
                    @Override
                    public void accept(List<CBannerBean> been) throws Exception {
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
        mDisposables.add(subscribe);
    }
}
