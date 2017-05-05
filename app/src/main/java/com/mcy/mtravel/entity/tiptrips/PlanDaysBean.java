package com.mcy.mtravel.entity.tiptrips;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/5.
 */

public class PlanDaysBean {
    /**
     * id : 5513
     * memo : 大阪本身是一个历史悠久的百川之城，沿穿城而过的水路漫游，既是大阪人的生活常态，也是游人感受水城气质的最好体验。更重要的是，每一条行走过的街道，每一个迎面而来的微笑，都勾勒出大阪生活之美，这样有人情味的城市让人感到无比的温暖和留恋。
     * #交通指南#
     * 建议购买大阪周游卡1日券，可随便乘坐大阪市内的地铁和巴士，还可免费入场28处旅游设施，2000日元/张，性价比非常高。
     */

    private int id;
    private String memo;
    private List<PlanNodesBean> plan_nodes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<PlanNodesBean> getPlan_nodes() {
        return plan_nodes;
    }

    public void setPlan_nodes(List<PlanNodesBean> plan_nodes) {
        this.plan_nodes = plan_nodes;
    }

}
