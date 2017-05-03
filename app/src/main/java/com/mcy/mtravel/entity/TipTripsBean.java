package com.mcy.mtravel.entity;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public class TipTripsBean {

    /**
     * id : 1693
     * name : 日本全景7日游
     * budget : 0
     * start_date : null
     * description : 京都的优雅，东京大阪的时尚，富士山的秀美，北海道的浪漫一次体验，7天最全面的日本游线路。
     * plan_days_count : 7
     * plan_nodes_count : 38
     * user_name :
     * image_url : http://m.chanyouji.cn/plans/1693.jpg
     * updated_at : 1410504676
     */

    private int id;
    private String name;
    private String description;
    private int plan_days_count;
    private int plan_nodes_count;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlan_days_count() {
        return plan_days_count;
    }

    public void setPlan_days_count(int plan_days_count) {
        this.plan_days_count = plan_days_count;
    }

    public int getPlan_nodes_count() {
        return plan_nodes_count;
    }

    public void setPlan_nodes_count(int plan_nodes_count) {
        this.plan_nodes_count = plan_nodes_count;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
