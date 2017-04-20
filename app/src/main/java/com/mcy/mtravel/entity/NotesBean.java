package com.mcy.mtravel.entity;

/**
 * Created by zhaojifeng on 2017/4/20.
 */

public class NotesBean {


    private int id;
    private String trip_date;
    private int day;
    private String entry_name;
    private int entry_id;
    private boolean user_entry;
    private String description;
    private PhotoBean photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoBean getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoBean photo) {
        this.photo = photo;
    }

    public String getTrip_date() {
        return trip_date;
    }

    public void setTrip_date(String trip_date) {
        this.trip_date = trip_date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getEntry_name() {
        return entry_name;
    }

    public void setEntry_name(String entry_name) {
        this.entry_name = entry_name;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public boolean isUser_entry() {
        return user_entry;
    }

    public void setUser_entry(boolean user_entry) {
        this.user_entry = user_entry;
    }
}
