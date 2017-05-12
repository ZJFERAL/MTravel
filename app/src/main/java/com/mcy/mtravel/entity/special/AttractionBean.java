package com.mcy.mtravel.entity.special;

/**
 * Created by zhaojifeng on 2017/5/12.
 */

public class AttractionBean {
    /**
     * id : 47050
     * name : 东京都
     * user_score : 4.6
     * attraction_trips_count : 343
     * image_url : http://m.chanyouji.cn/attractions/47050.jpg
     */

    private int id;
    private String name;
    private String user_score;
    private int attraction_trips_count;
    private String image_url;

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

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public int getAttraction_trips_count() {
        return attraction_trips_count;
    }

    public void setAttraction_trips_count(int attraction_trips_count) {
        this.attraction_trips_count = attraction_trips_count;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
