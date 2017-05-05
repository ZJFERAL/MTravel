package com.mcy.mtravel.entity.tip;

/**
 * Created by zhaojifeng on 2017/4/26.
 */

public class StrategyBean {

    private int id;
    private String name_zh_cn;
    private String image_url;
    private String name_en;

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }
}
