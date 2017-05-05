package com.mcy.mtravel.entity.tipwiki;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/27.
 */

public class PagesBean {
    /**
     * id : 3
     * title : 游玩指南
     */

    private int id;
    private String title;
    private List<ChildrenBean> children;

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

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }
}
