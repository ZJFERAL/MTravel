package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.tip.TipTripsBean;
import com.mcy.mtravel.model.impl.TipTripsListModelImpl;
import com.mcy.mtravel.utils.FinalParams;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jifengZhao on 2017/5/3.
 */

public class TipTripsListModel implements TipTripsListModelImpl {

    private String mID;
    private CompositeDisposable mCompositeDisposable;
    private CyjUrl mUrl;
    private int index = 1;

    public TipTripsListModel(String travelListID) {
        mID = travelListID;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getData(final OnAsyncModelListener<List<TipTripsBean>> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null, App.getInstance()).create(CyjUrl.class);
        }
        Disposable subscribe = mUrl.getTipTripsList(mID, index + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TipTripsBean>>() {
                    @Override
                    public void accept(List<TipTripsBean> been) throws Exception {
                        if (been != null) {
                            if (been.size() != 0) {
                                listener.onSuccess(been);
                                index++;
                            } else {
                                listener.onFailure(App.getStringRes(R.string.no_more_data), FinalParams.ERROR_INFO);
                            }
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
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public void onRefreshData(OnAsyncModelListener<List<TipTripsBean>> listener) {
        index = 1;
        getData(listener);
    }


    @Override
    public void cancel() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
