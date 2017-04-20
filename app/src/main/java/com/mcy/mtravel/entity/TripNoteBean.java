package com.mcy.mtravel.entity;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/20.
 */

public class TripNoteBean {


    private int id;
    private String name;
    private int photos_count;
    private String start_date;
    private int level;
    private UserBean user;
    private String front_cover_photo_url;
    private List<TripDaysBean> trip_days;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }


    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public List<TripDaysBean> getTrip_days() {
        return trip_days;
    }

    public void setTrip_days(List<TripDaysBean> trip_days) {
        this.trip_days = trip_days;
    }

}
