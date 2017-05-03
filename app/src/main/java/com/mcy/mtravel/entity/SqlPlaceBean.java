package com.mcy.mtravel.entity;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public class SqlPlaceBean {
    private int _id;
    private String time;
    private String place_id;
    private String name;

    public SqlPlaceBean() {
    }

    public SqlPlaceBean(int _id, String time, String place_id, String name) {
        this._id = _id;
        this.time = time;
        this.place_id = place_id;
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
