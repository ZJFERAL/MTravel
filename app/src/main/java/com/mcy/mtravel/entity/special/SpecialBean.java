package com.mcy.mtravel.entity.special;

import java.util.List;

/**
 * Created by jifengZhao on 2017/5/12.
 */

public class SpecialBean {


    private int id;
    private String name;
    private String image_url;
    private String title;
    private int comments_count;
    private int destination_id;
    private boolean commentable;
    private boolean current_user_favorite;
    private int updated_at;
    private List<ArticleSectionsBean> article_sections;

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

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(int destination_id) {
        this.destination_id = destination_id;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public boolean isCurrent_user_favorite() {
        return current_user_favorite;
    }

    public void setCurrent_user_favorite(boolean current_user_favorite) {
        this.current_user_favorite = current_user_favorite;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public List<ArticleSectionsBean> getArticle_sections() {
        return article_sections;
    }

    public void setArticle_sections(List<ArticleSectionsBean> article_sections) {
        this.article_sections = article_sections;
    }

}
