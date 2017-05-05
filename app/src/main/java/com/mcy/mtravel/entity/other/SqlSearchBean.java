package com.mcy.mtravel.entity.other;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public class SqlSearchBean {
    private int _id;
    private String time;
    private String keyword;

    public SqlSearchBean(int _id, String time, String keyword) {
        this._id = _id;
        this.time = time;
        this.keyword = keyword;
    }

    public SqlSearchBean() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
