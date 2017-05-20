package com.mcy.mtravel.entity.tiptrips;

import com.mcy.mtravel.entity.tip.DestinationBean;

/**
 * Created by machengyuan on 2017/5/5.
 */

public class PlanNodesBean {
    /**
     * id : 174614
     * entry_id : 156560
     * position : 0
     * candidate : false
     * tips : #国内到大阪# 从国内前往大阪的航班都降落在关西国际机场，从机场前往大阪市区可搭乘：南海电铁空港急行往返难波站（890日元、约42分钟）或关空机场巴士往返难波站（1,000日元、50分）等多种方式。
     * lat : 34.43489
     * lng : 135.244451
     * distance : 0
     * image_url : http://m.chanyouji.cn/attractions/156560.jpg
     * entry_name : 关西国际机场
     * entry_type : Attraction
     * attraction_type : transport
     * user_entry : false
     * destination : {"id":167,"name_zh_cn":"关西"}
     */

    private int id;
    private int entry_id;
    private int position;
    private boolean candidate;
    private String tips;
    private String lat;
    private String lng;
    private String image_url;
    private String entry_name;
    private String entry_type;
    private String attraction_type;
    private boolean user_entry;
    private String firstTitle;
    private DestinationBean destination;

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isCandidate() {
        return candidate;
    }

    public void setCandidate(boolean candidate) {
        this.candidate = candidate;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getEntry_name() {
        return entry_name;
    }

    public void setEntry_name(String entry_name) {
        this.entry_name = entry_name;
    }

    public String getEntry_type() {
        return entry_type;
    }

    public void setEntry_type(String entry_type) {
        this.entry_type = entry_type;
    }

    public String getAttraction_type() {
        return attraction_type;
    }

    public void setAttraction_type(String attraction_type) {
        this.attraction_type = attraction_type;
    }

    public boolean isUser_entry() {
        return user_entry;
    }

    public void setUser_entry(boolean user_entry) {
        this.user_entry = user_entry;
    }

    public DestinationBean getDestination() {
        return destination;
    }

    public void setDestination(DestinationBean destination) {
        this.destination = destination;
    }
}
