package com.mcy.mtravel.entity;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/27.
 */

public class StrategyDetialBean {


    /**
     * category_type : 0
     */

    private int category_type;
    private List<PagesBean> pages;

    public int getCategory_type() {
        return category_type;
    }

    public void setCategory_type(int category_type) {
        this.category_type = category_type;
    }

    public List<PagesBean> getPages() {
        return pages;
    }

    public void setPages(List<PagesBean> pages) {
        this.pages = pages;
    }

}
