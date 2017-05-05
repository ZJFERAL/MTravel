package com.mcy.mtravel.model;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.api.CyjUrl;
import com.mcy.mtravel.entity.tip.TipTripsBean;
import com.mcy.mtravel.utils.FinalParams;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.model.BaseSingleModel;
import com.zjf.core.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhaojifeng on 2017/5/5.
 */

public class TravelDetialModel extends BaseSingleModel<TipTripsBean> {

    private String mID;
    private CyjUrl mUrl;

    public TravelDetialModel(String ID) {
        mID = ID;
    }


    @Override
    protected void getData(final OnAsyncModelListener<TipTripsBean> listener) {
        if (mUrl == null) {
            mUrl = RetrofitUtils.getClient(FinalParams.CY_APP_BASEURL, null, App.getInstance()).create(CyjUrl.class);
        }
        mUrl.getTipTripDetial(mID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TipTripsBean>() {
                    @Override
                    public void accept(TipTripsBean bean) throws Exception {
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
    }
}
