package com.mcy.mtravel.entity.trips;

/**
 * @author :ZJF
 * @version : 2017-04-20 下午 9:01
 */

public class PhotoBean {
    private int id;
    private int image_width;
    private int image_height;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
