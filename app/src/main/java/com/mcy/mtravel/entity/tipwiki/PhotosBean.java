package com.mcy.mtravel.entity.tipwiki;

/**
 * Created by machengyuan on 2017/4/27.
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

    private int id;
    private String image_url;
    private int image_width;
    private int image_height;
    private String description;
    private int trip_id;
    private int likes_count;
    private int note_id;
    private String user_name;
    private String trip_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }
}
