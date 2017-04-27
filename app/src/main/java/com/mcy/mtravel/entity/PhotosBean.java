package com.mcy.mtravel.entity;

/**
 * Created by zhaojifeng on 2017/4/27.
 */

public class PhotosBean {
    /**
     * image_url : http://w.chanyouji.cn/1405591989250p18t1svavvq3kafl1tfkc4665r1.jpg
     * image_width : 1100
     * image_height : 1216
     * description : 日本概览地图
     * trip_id : null
     * note_id : null
     * user_name : null
     */

    private String image_url;
    private int image_width;
    private int image_height;
    private String description;
    private Object trip_id;
    private Object note_id;
    private Object user_name;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getImage_width() {
        return image_width;
    }

    public void setImage_width(int image_width) {
        this.image_width = image_width;
    }

    public int getImage_height() {
        return image_height;
    }

    public void setImage_height(int image_height) {
        this.image_height = image_height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Object trip_id) {
        this.trip_id = trip_id;
    }

    public Object getNote_id() {
        return note_id;
    }

    public void setNote_id(Object note_id) {
        this.note_id = note_id;
    }

    public Object getUser_name() {
        return user_name;
    }

    public void setUser_name(Object user_name) {
        this.user_name = user_name;
    }
}
