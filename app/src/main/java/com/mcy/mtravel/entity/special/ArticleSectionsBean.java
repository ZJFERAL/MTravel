package com.mcy.mtravel.entity.special;

/**
 * Created by machengyuan on 2017/5/12.
 */

public class ArticleSectionsBean {
    /**
     * title :
     * image_url :
     * image_width : 0
     * image_height : 0
     * description_user_ids : {}
     * description : 日本，由北海道、本州、四国、九州四个大岛和六千多个小岛组成的东亚岛国。这里有漫长的美丽海岸线，贯穿全境的山川湖泊，各种火山温泉以及冲绳适宜潜水的蓝洞和珊瑚礁。还有古朴的建筑，多元的文化，精致的美食，无论你是日剧迷、二次元、铁路控、吃货、电器迷……日本都能给你爱上它的理由。
     * attraction : {"id":47050,"name":"东京都","user_score":"4.6","attraction_trips_count":343,"image_url":"http://m.chanyouji.cn/attractions/47050.jpg"}
     * note : {"id":2789220,"trip_id":66896,"trip_name":"冲绳\u2014琉球国的巡礼","user_name":"聪聪匆匆溜走"}
     */

    private String title;
    private String image_url;
    private int image_width;
    private int image_height;
    private String description;
    private AttractionBean attraction;
    private NoteBean note;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AttractionBean getAttraction() {
        return attraction;
    }

    public void setAttraction(AttractionBean attraction) {
        this.attraction = attraction;
    }

    public NoteBean getNote() {
        return note;
    }

    public void setNote(NoteBean note) {
        this.note = note;
    }
}
