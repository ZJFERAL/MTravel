package com.mcy.mtravel.entity.travel;

/**
 * Created by zhaojifeng on 2017/5/12.
 */

public class HotelsBean {
    /**
     * id : 128520
     * name_zh_cn : & Hana Stay
     * distance : 0.618942865869595
     * image_url : http://m.chanyouji.cn/hotels/128520.jpg
     */

    private int id;
    private String name_zh_cn;
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
