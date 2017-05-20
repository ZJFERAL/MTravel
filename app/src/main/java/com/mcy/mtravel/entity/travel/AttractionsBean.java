package com.mcy.mtravel.entity.travel;

/**
 * Created by machengyuan on 2017/5/12.
 */

public class AttractionsBean {
    /**
     * id : 158068
     * name_zh_cn : 京都清水坂
     * user_score : 4.0
     * distance : 0.00800655291183974
     * image_url : http://m.chanyouji.cn/attractions/158068.jpg
     */

    private int id;
    private String name_zh_cn;
    private String user_score;
    private double distance;
    private String image_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_zh_cn() {
        return name_zh_cn;
    }

    public void setName_zh_cn(String name_zh_cn) {
        this.name_zh_cn = name_zh_cn;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
