package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.TripNoteBean;
import com.mcy.mtravel.model.impl.TripsNoteModelImpl;
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
 * Created by zhaojifeng on 2017/4/20.
 */

public class TripsNoteModel implements TripsNoteModelImpl {

    private String mTripsID;
    private List<Disposable> mDisposables;
    private CyjUrl mUrl;

    public TripsNoteModel(String tripsID) {
        mTripsID = tripsID;
        mDisposables = new ArrayList<>();
    }

    @Override
    public void getData(final OnAsyncModelListener<TripNoteBean> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null).create(CyjUrl.class);
        }
        Disposable subscribe = mUrl.getTripsNote(mTripsID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<TripNoteBean>() {
                    @Override
                    public void accept(TripNoteBean bean) throws Exception {
                        if (bean != null) {
                            listener.onSuccess(bean);
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
}
