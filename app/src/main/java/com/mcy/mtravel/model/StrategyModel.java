package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.tip.StrategyBean;
import com.mcy.mtravel.model.impl.StrategyModelImpl;
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
 * Created by zhaojifeng on 2017/4/26.
 */

public class StrategyModel implements StrategyModelImpl {

    private String mStrategyID;
    private CompositeDisposable mCompositeDisposable;
    private CyjUrl mUrl;

    public StrategyModel(String strategyID) {
        mStrategyID = strategyID;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getData(final OnAsyncModelListener<List<StrategyBean>> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null, App.getInstance()).create(CyjUrl.class);
        }
        Disposable subscribe = mUrl.getStrategy(mStrategyID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<StrategyBean>>() {
                    @Override
                    public void accept(List<StrategyBean> list) throws Exception {
                        if (list != null && list.size() != 0) {
                            listener.onSuccess(list);
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
    public void cancel() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
