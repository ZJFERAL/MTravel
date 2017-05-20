package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.tipwiki.StrategyDetialBean;
import com.mcy.mtravel.utils.FinalParams;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseAsyncModelImp;
import com.zjf.core.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by machengyuan on 2017/4/27.
 */

public class TipModel implements BaseAsyncModelImp<List<StrategyDetialBean>> {

    private static TipModel mTipModel;
    private static String mID = "";
    private static List<StrategyDetialBean> mDetialBeen;

    private TipModel() {
        mDisposables = new ArrayList<>();
    }

    public static TipModel getInstance(String tipID) {
        if (!tipID.equals(mID)) {
            mID = tipID;
            mDetialBeen = new ArrayList<>();
            mDetialBeen.clear();
        }
        if (mTipModel == null) {
            synchronized (TipModel.class) {
                if (mTipModel == null) {
                    mTipModel = new TipModel();
                }
            }
        }
        return mTipModel;
    }

    private List<Disposable> mDisposables;
    private CyjUrl mUrl;

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
    public void getData(final OnAsyncModelListener<List<StrategyDetialBean>> listener) {
        if (mDetialBeen != null && mDetialBeen.size() != 0) {
            listener.onSuccess(mDetialBeen);
            return;
        }
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL,
                    null, App.getInstance(), RetrofitUtils.TYPE_LONG).create(CyjUrl.class);
        }
        mUrl.getWikiTips(mID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<StrategyDetialBean>>() {
                    @Override
                    public void accept(List<StrategyDetialBean> list) throws Exception {
                        if (list != null && list.size() > 0) {
                            mDetialBeen.addAll(list);
                            listener.onSuccess(mDetialBeen);
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
