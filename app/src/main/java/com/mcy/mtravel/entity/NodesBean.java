package com.mcy.mtravel.entity;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/20.
 */

public class NodesBean {
    private int id;
    private int updated_at;
    private List<NotesBean> notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public List<NotesBean> getNotes() {
        return notes;
    }

    public void setNotes(List<NotesBean> notes) {
        this.notes = notes;
    }

}
