package com.mcy.mtravel.entity;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/21.
 */

public class UserWithTripsBean extends UserBean{


    private int gender;
    private int trips_count;
    private List<TripsBean> trips;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getTrips_count() {
        return trips_count;
    }

    public void setTrips_count(int trips_count) {
        this.trips_count = trips_count;
    }

    public List<TripsBean> getTrips() {
        return trips;
    }

    public void setTrips(List<TripsBean> trips) {
        this.trips = trips;
    }
}
