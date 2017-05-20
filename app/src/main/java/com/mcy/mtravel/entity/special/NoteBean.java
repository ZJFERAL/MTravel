package com.mcy.mtravel.entity.special;

/**
 * Created by jifengZhao on 2017/5/12.
 */

public class NoteBean {
    /**
     * id : 2789220
     * trip_id : 66896
     * trip_name : 冲绳—琉球国的巡礼
     * user_name : 聪聪匆匆溜走
     */

    private int id;
    private int trip_id;
    private String trip_name;
    private String user_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
