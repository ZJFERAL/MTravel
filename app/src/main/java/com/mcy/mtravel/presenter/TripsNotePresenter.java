package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.TripNoteBean;
import com.mcy.mtravel.model.TripsNoteModel;
import com.mcy.mtravel.model.impl.TripsNoteModelImpl;
import com.mcy.mtravel.view.impl.TripsNoteView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

/**
 * Created by zhaojifeng on 2017/4/20.
 */

public class TripsNotePresenter extends Presenter<TripsNoteView> {

    private TripsNoteModelImpl mNoteModel;
    private String mTripsID;


    public TripsNotePresenter(String tripsID) {
        mTripsID = tripsID;
        mNoteModel = new TripsNoteModel(mTripsID);
    }

    @Override
    protected void onViewCreated() {
        mNoteModel.getData(new OnAsyncModelListener<TripNoteBean>() {
            @Override
            public void onFailure(String msg, int type) {

            }

            @Override
            public void onSuccess(TripNoteBean msg) {

            }
        });
    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        if (mNoteModel != null) {
            mNoteModel.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mNoteModel = null;
    }
}
