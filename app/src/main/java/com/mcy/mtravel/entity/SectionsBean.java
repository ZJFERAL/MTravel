package com.mcy.mtravel.entity;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/27.
 */

public class SectionsBean {
    /**
     * id : 100
     * title : 概览与地图
     * description : 日本旅游目的地众多，以东京为中心的关东地区和以京都、大阪、神户为中心的关西地区是最热门的旅游地，遍布全境的樱花和天然温泉是其显著的特色。
     * 相近的文字，完善友好的公共设施使得自由行非常容易。国内飞涨的物价已让日本的优质商品有了性价比，到了打折季节更会让人欲罢不能，加上与国人近似的饮食口味和高质量的天然食材，这里无疑还是吃货向往的天堂。
     * travel_date : null
     * : null
     * attractions : []
     * hotels : []
     * pages : []
     * photos : [{"image_url":"http://w.chanyouji.cn/1405591989250p18t1svavvq3kafl1tfkc4665r1.jpg","image_width":1100,"image_height":1216,"description":"日本概览地图","trip_id":null,"note_id":null,"user_name":null}]
     * items : []
     */

    private int id;
    private String title;
    private String description;
    private List<PhotosBean> photos;
    private String travel_date;
    private UserBean user;
    private List<Pages> pages;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public String getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(String travel_date) {
        this.travel_date = travel_date;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<Pages> getPages() {
        return pages;
    }

    public void setPages(List<Pages> pages) {
        this.pages = pages;
    }
}
