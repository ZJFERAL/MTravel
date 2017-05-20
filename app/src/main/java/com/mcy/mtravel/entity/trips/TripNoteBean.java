package com.mcy.mtravel.entity.trips;

import com.mcy.mtravel.entity.index.TripsBean;

import java.util.List;

/**
 * Created by machengyuan on 2017/4/20.
 */

public class TripNoteBean extends TripsBean {

    private List<TripDaysBean> trip_days;

    public List<TripDaysBean> getTrip_days() {
        return trip_days;
    }

    public void setTrip_days(List<TripDaysBean> trip_days) {
        this.trip_days = trip_days;
    }

}
