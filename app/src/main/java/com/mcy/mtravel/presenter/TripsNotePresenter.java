package com.mcy.mtravel.presenter;

import android.text.TextUtils;

import com.mcy.mtravel.entity.trips.NodesBean;
import com.mcy.mtravel.entity.trips.NotesBean;
import com.mcy.mtravel.entity.trips.TripDaysBean;
import com.mcy.mtravel.entity.trips.TripNoteBean;
import com.mcy.mtravel.model.TripsNoteModel;
import com.mcy.mtravel.model.impl.TripsNoteModelImpl;
import com.mcy.mtravel.view.impl.TripsNoteView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;
import com.zjf.core.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machengyuan on 2017/4/20.
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
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(TripNoteBean msg) {
                mView.onTitleData(msg);
                makeData(msg);
            }
        });
    }


    private void makeData(TripNoteBean msg) {
        List<TripDaysBean> days = msg.getTrip_days();
        int size = days.size();
        List<String> dayTitles = new ArrayList<>();
        List<List<String>> items = new ArrayList<>();
        List<NotesBean> notesBeens = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TripDaysBean daysBean = days.get(i);
            int day = daysBean.getDay();
            dayTitles.add("" + day);
            String trip_date = daysBean.getTrip_date();
            List<NodesBean> nodes = daysBean.getNodes();
            int nodesSize = nodes.size();
            List<String> item_items = new ArrayList<>();
            for (int j = 0; j < nodesSize; j++) {
                NodesBean nodesBean = nodes.get(j);
                int entry_id = nodesBean.getEntry_id();
                String entry_name = nodesBean.getEntry_name();
                boolean user_entry = nodesBean.isUser_entry();
                if (!TextUtils.isEmpty(entry_name)) {
                    item_items.add(entry_name);
                    LogUtils.e(i + " " + j);
                }
                List<NotesBean> notes = nodesBean.getNotes();
                int notesSize = notes.size();
                if ((!TextUtils.isEmpty(entry_name)) && (nodes == null || notesSize == 0)) {
                    NotesBean notesBean = new NotesBean();
                    notesBean.setEntry_id(entry_id);
                    notesBean.setEntry_name(entry_name);
                    notesBean.setUser_entry(user_entry);
                    notesBean.setDay(day);
                    notesBean.setTrip_date(trip_date);
                    notesBeens.add(notesBean);
                }
                for (int k = 0; k < notesSize; k++) {
                    NotesBean notesBean = notes.get(k);
                    notesBean.setEntry_id(entry_id);
                    notesBean.setEntry_name(entry_name);
                    notesBean.setUser_entry(user_entry);
                    notesBean.setDay(day);
                    notesBean.setTrip_date(trip_date);
                    notesBeens.add(notesBean);
                }
            }
            items.add(item_items);
        }
        mView.onLeftView(dayTitles, items);
        mView.onRecyclerViewData(notesBeens);
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
