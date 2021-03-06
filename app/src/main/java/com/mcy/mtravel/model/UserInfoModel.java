package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.user.UserWithTripsBean;
import com.mcy.mtravel.model.impl.UserInfoModelImpl;
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
 * Created by jifengZhao on 2017/4/21.
 */

public class UserInfoModel implements UserInfoModelImpl {

    private String mUserID;
    private List<Disposable> mDisposables;
    private CyjUrl mUrl;
    private int currentIndex = 1;


    public UserInfoModel(String userID) {
        mUserID = userID;
        mDisposables = new ArrayList<>();
    }

    @Override
    public void getData(final OnAsyncModelListener<UserWithTripsBean> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null, App.getInstance()).create(CyjUrl.class);
        }

        Disposable subscribe = mUrl.getUser(mUserID, currentIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<UserWithTripsBean>() {
                    @Override
                    public void accept(UserWithTripsBean bean) throws Exception {
                        if (bean != null) {
                            listener.onSuccess(bean);
                            currentIndex++;
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
    public void onRefreshData(OnAsyncModelListener<UserWithTripsBean> listener) {
        currentIndex = 1;
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
