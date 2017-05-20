package com.mcy.mtravel.entity.tipwiki;

import java.util.List;

/**
 * Created by jifengZhao on 2017/4/27.
 */

public class ChildrenBean {
    /**
     * id : 4
     * title : 日本游玩概览
     */

    private int id;
    private String title;
    private List<SectionsBean> sections;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SectionsBean> getSections() {
        return sections;
    }

    public void setSections(List<SectionsBean> sections) {
        this.sections = sections;
    }
}
