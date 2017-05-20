package com.mcy.mtravel.entity.tip;

/**
 * Created by jifengZhao on 2017/5/3.
 */

public class SpecialListBean {

    /**
     * id : 76
     * name : 醉｜日本
     * image_url : http://p.chanyouji.cn/34062/1367929106035p17pverbioeog1pu2i1mirb11fd2.jpg
     * title : 日式小清新体验之旅
     * destination_id : 55
     * updated_at : 1428458258
     */

    private int id;
    private String name;
    private String image_url;
    private String title;
    private int destination_id;

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }
}
