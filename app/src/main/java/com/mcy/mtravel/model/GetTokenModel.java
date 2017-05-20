package com.mcy.mtravel.model;

import android.text.TextUtils;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.QyerUrl;
import com.mcy.mtravel.entity.user.TokenBean;
import com.mcy.mtravel.model.impl.GetTokenModelImpl;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.utils.SPUtils;
import com.mcy.mtravel.utils.UrlUtils;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.NetUtils;
import com.zjf.core.utils.RetrofitUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jifengZhao on 2017/4/14.
 */

public class GetTokenModel implements GetTokenModelImpl {

    private SPUtils mSPUtils;
    private boolean isFirst = false;
    private Disposable mSubscribe;

    public GetTokenModel() {
        mSPUtils = new SPUtils();
        isFirst = mSPUtils.getFirstFlag();
    }

    @Override
    public void getData(OnAsyncModelListener<String> listener) {
        String token = mSPUtils.getToken();
        if (isFirst || TextUtils.isEmpty(token)) {
            if (NetUtils.isNetworkReachable(App.getInstance())) {
                startNetWork(listener);
            } else {
                listener.onFailure(App.getStringRes(R.string.error_net), FinalParams.ERROR_ALERT);
            }
        } else {
            listener.onSuccess(token);
        }
    }

    private void startNetWork(final OnAsyncModelListener<String> listener) {
        QyerUrl qyerUrl = RetrofitUtils.getClient(FinalParams.QY_APP_BASEURL, null, App.getInstance()).create(QyerUrl.class);
        Observable<TokenBean> token = null;
        if (isFirst) {
            token = qyerUrl.getInitToken("",
                    UrlUtils.getTokenAuthorization(isFirst),
                    FinalParams.CONTENT_TYPE, FinalParams.QY_APP_ID,
                    FinalParams.QY_APP_VERSION, DeviceUtils.getDeviceId(App.getInstance()), 1);
        } else {
            token = qyerUrl.getToken("",
                    UrlUtils.getTokenAuthorization(isFirst),
                    FinalParams.CONTENT_TYPE, FinalParams.QY_APP_ID,
                    FinalParams.QY_APP_VERSION, DeviceUtils.getDeviceId(App.getInstance()));
        }
        if (token == null) {
            listener.onFailure(App.getStringRes(R.string.error_token), FinalParams.ERROR_INFO);
            return;
        }
        mSubscribe = token.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TokenBean>() {
                    @Override
                    public void accept(TokenBean bean) throws Exception {
                        if (bean != null && "ok".equals(bean.getResult())) {
                            String msg = bean.getData().getToken();
                            LogUtils.e("getaToken", "OK" + msg);
                            listener.onSuccess(msg);
                        } else {
                            listener.onFailure(App.getStringRes(R.string.error_token), FinalParams.ERROR_INFO);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onFailure(throwable.getMessage(), FinalParams.ERROR_INFO);
                    }
                });
    }

    @Override
    public void cancel() {
        if (mSubscribe != null && (!mSubscribe.isDisposed())) {
            mSubscribe.dispose();
        }
    }
}
