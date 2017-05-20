package com.mcy.mtravel.entity.travel;

/**
 * Created by jifengZhao on 2017/5/12.
 */

public class TravelNotesBean {
    /**
     * id : 3684607
     * description :
     * width : 1600
     * height : 900
     * photo_url : http://p.chanyouji.cn/93844/1388465098531p18d3et14i1gg7o6a3u8t3r10el1t.jpg
     */

    private int id;
    private String description;
    private int width;
    private int height;
    private String photo_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
