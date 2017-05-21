package com.mcy.mtravel.entity.travel;

import java.util.List;

/**
 * Created by jifengZhao on 2017/5/12.
 */

public class AttractionContentsBean {
    /**
     * id : 9316
     * description : #京都的名片# 清水寺与金阁寺、岚山等同为京都境内最著名的名胜古迹，一年四季前来朝拜的香客或来访的观光客是络驿不绝。 清水寺是京都最古老的寺院，建于公元798年，清水寺的山号为音羽山，主要供奉千手观音。
     * updated_at : 1389873224
     * node_id : 713048
     * node_comments_count : 0
     * trip : {"id":93844,"name":"京都红叶狩","photos_count":123,"days":4,"start_date":"2013-11-28","end_date":"2013-12-01","level":3,"privacy":false,"views_count":5844,"comments_count":9,"likes_count":102,"state":"publish","source":"web","serial_id":null,"serial_position":null,"front_cover_photo_url":"http://cyj.qiniudn.com/93844/1388465555906p18d3et14ihcf6n1jmjaac1qmj2v.jpg","updated_at":1408616705,"user":{"id":34757,"name":"芒椰西米","image":"http://tp2.sinaimg.cn/1849354581/180/5671970414/0"}}
     * notes : [{"id":3684607,"description":"","width":1600,"height":900,"photo_url":"http://p.chanyouji.cn/93844/1388465098531p18d3et14i1gg7o6a3u8t3r10el1t.jpg","video_url":null},{"id":3684578,"description":"红叶簇拥的清水舞台","width":1600,"height":1200,"photo_url":"http://p.chanyouji.cn/93844/1388464746265p18d3et14i2k01njm13opg2c39n10.jpg","video_url":null},{"id":3684579,"description":"清水寺三重塔","width":1600,"height":1200,"photo_url":"http://p.chanyouji.cn/93844/1388464753718p18d3et14i1hgdq16l6f1cgb17fb11.jpg","video_url":null}]
     */

    private boolean featured;
    private int id;
    private String description;
    private int updated_at;
    private int photos_count;
    private int node_id;
    private String comment;
    private String title;
    private int node_comments_count;
    private TravelTripBean trip;
    private List<TravelNotesBean> notes;

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

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public int getNode_comments_count() {
        return node_comments_count;
    }

    public void setNode_comments_count(int node_comments_count) {
        this.node_comments_count = node_comments_count;
    }

    public TravelTripBean getTrip() {
        return trip;
    }

    public void setTrip(TravelTripBean trip) {
        this.trip = trip;
    }

    public List<TravelNotesBean> getNotes() {
        return notes;
    }

    public void setNotes(List<TravelNotesBean> notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
