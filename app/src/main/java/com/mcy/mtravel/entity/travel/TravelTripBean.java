package com.mcy.mtravel.entity.travel;

import com.mcy.mtravel.entity.user.UserBean;

/**
 * Created by zhaojifeng on 2017/5/12.
 */

public class TravelTripBean {
    /**
     * id : 93844
     * name : 京都红叶狩
     * photos_count : 123
     * days : 4
     * start_date : 2013-11-28
     * end_date : 2013-12-01
     * level : 3
     * privacy : false
     * views_count : 5844
     * comments_count : 9
     * likes_count : 102
     * state : publish
     * source : web
     * serial_id : null
     * serial_position : null
     * front_cover_photo_url : http://cyj.qiniudn.com/93844/1388465555906p18d3et14ihcf6n1jmjaac1qmj2v.jpg
     * updated_at : 1408616705
     * user : {"id":34757,"name":"芒椰西米","image":"http://tp2.sinaimg.cn/1849354581/180/5671970414/0"}
     */

    private int id;
    private String name;
    private int photos_count;
    private int days;
    private String start_date;
    private String end_date;
    private int level;
    private boolean privacy;
    private int views_count;
    private int comments_count;
    private int likes_count;
    private String state;
    private String source;
    private String front_cover_photo_url;
    private int updated_at;
    private UserBean user;

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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
