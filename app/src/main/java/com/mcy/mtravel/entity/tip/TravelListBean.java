package com.mcy.mtravel.entity.tip;

/**
 * Created by machengyuan on 2017/5/3.
 */

public class TravelListBean {

    /**
     * id : 35443
     * name : 京都清水寺
     * attraction_trips_count : 997
     * user_score : 4.34
     * description : 清水寺依山势而建的、靠数百根巨大的圆木撑起的大殿露台“清水舞台”。
     * name_en : Kiyomizu Temple
     * attraction_type : null
     * description_summary : 清水寺依山势而建的、靠数百根巨大的圆木撑起的大殿露台“清水舞台”。
     * image_url : http://m.chanyouji.cn/attractions/35443.jpg
     */

    private int id;
    private String name;
    private int attraction_trips_count;
    private String user_score;
    private String description;
    private String name_en;
    private String description_summary;
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

    public int getAttraction_trips_count() {
        return attraction_trips_count;
    }

    public void setAttraction_trips_count(int attraction_trips_count) {
        this.attraction_trips_count = attraction_trips_count;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String user_score) {
        this.user_score = user_score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getDescription_summary() {
        return description_summary;
    }

    public void setDescription_summary(String description_summary) {
        this.description_summary = description_summary;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
