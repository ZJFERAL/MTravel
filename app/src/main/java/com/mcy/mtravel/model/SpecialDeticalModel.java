package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.special.SpecialBean;
import com.mcy.mtravel.utils.FinalParams;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseSingleModel;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author :ZJF
 * @version : 2017-05-20 下午 3:23
 */

public class SpecialDeticalModel extends BaseSingleModel<SpecialBean> {

    private CyjUrl mUrl;
    private String mID;

    public SpecialDeticalModel(String ID) {
        mID = ID;
    }

    @Override
    public void getData(final OnAsyncModelListener<SpecialBean> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null, App.getInstance()).create(CyjUrl.class);
        }
        Disposable disposable = mUrl.getSpecialDetial(mID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SpecialBean>() {
                    @Override
                    public void accept(SpecialBean bean) throws Exception {
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
                        LogUtils.e("Throwable", throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
