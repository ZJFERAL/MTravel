package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.QyerUrl;
import com.mcy.mtravel.entity.IndexBean;
import com.mcy.mtravel.model.impl.NewsModelImpl;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.utils.SPUtils;
import com.mcy.mtravel.utils.UrlUtils;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public class NewsModel implements NewsModelImpl {

    private SPUtils mSPUtils;
    private int currentIndex = 0;
    private List<Disposable> mDisposables;

    public NewsModel() {
        mSPUtils = new SPUtils();
        mDisposables = new ArrayList<>();
    }

    @Override
    public void getData(final OnAsyncModelListener<IndexBean> listener) {
        String token = mSPUtils.getToken();
        QyerUrl qyerUrl = RetrofitUtils.getClient(FinalParams.QY_APP_BASEURL, null).create(QyerUrl.class);
        Observable<IndexBean> indexUrl = qyerUrl.getIndex(FinalParams.QY_APP_INDEXURL, token, UrlUtils.getIndexAuthorization(currentIndex),
                FinalParams.CONTENT_TYPE,
                "feed", "10", "" + currentIndex * 10);
        Disposable subscribe = indexUrl.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IndexBean>() {
                    @Override
                    public void accept(IndexBean bean) throws Exception {
                        if (bean != null && "ok".equals(bean.getResult())) {
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
        currentIndex++;
    }

    @Override
    public void getRefreshData(OnAsyncModelListener<IndexBean> listener) {
        currentIndex = 0;
        getData(listener);
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
