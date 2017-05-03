package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.SpecialListBean;
import com.mcy.mtravel.model.impl.SpecialModelImpl;
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
 * Created by zhaojifeng on 2017/5/3.
 */

public class SpecialModel implements SpecialModelImpl {
    private String mID;
    private List<Disposable> mDisposables;
    private CyjUrl mUrl;
    private int index = 1;

    public SpecialModel(String ID) {
        mID = ID;
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
    public void getData(final OnAsyncModelListener<List<SpecialListBean>> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null, App.getInstance()).create(CyjUrl.class);
        }
        mUrl.getSpecialList(mID, index + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<SpecialListBean>>() {
                    @Override
                    public void accept(List<SpecialListBean> been) throws Exception {
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
    }

    @Override
    public void onRefreshData(OnAsyncModelListener<List<SpecialListBean>> listener) {
        index = 1;
        getData(listener);
    }
}
