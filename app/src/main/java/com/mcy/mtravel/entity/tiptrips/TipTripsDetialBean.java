package com.mcy.mtravel.entity.tiptrips;

import java.util.List;

/**
 * Created by jifengZhao on 2017/5/5.
 */

public class TipTripsDetialBean {

    private int id;
    private String name;
    private String description;
    private int budget;
    private String budget_description;
    private String tips;
    private boolean current_user_favorite;
    private int updated_at;
    private String image_url;
    private List<PlanDaysBean> plan_days;

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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getBudget_description() {
        return budget_description;
    }

    public void setBudget_description(String budget_description) {
        this.budget_description = budget_description;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<PlanDaysBean> getPlan_days() {
        return plan_days;
    }

    public void setPlan_days(List<PlanDaysBean> plan_days) {
        this.plan_days = plan_days;
    }

}
