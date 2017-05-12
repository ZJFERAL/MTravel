package com.mcy.mtravel.presenter;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tiptrips.PlanDaysBean;
import com.mcy.mtravel.entity.tiptrips.PlanNodesBean;
import com.mcy.mtravel.entity.tiptrips.TipTripsDetialBean;
import com.mcy.mtravel.model.TripsDetialModel;
import com.mcy.mtravel.view.impl.TripsDetialView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/5.
 */

public class TripsDetialPresenter extends Presenter<TripsDetialView> {

    private TripsDetialModel mModel;
    private String mID;

    public TripsDetialPresenter(String id) {
        mID = id;
        mModel = new TripsDetialModel(mID);
    }

    @Override
    protected void onViewCreated() {
        mModel.getData(new OnAsyncModelListener<TipTripsDetialBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(TipTripsDetialBean msg) {
                makeData(msg);
            }
        });
    }

    private void makeData(TipTripsDetialBean msg) {
        List<PlanDaysBean> days = msg.getPlan_days();
        List<String> menuTitle = new ArrayList<>();
        List<List<String>> menuItem = new ArrayList<>();
        List<PlanNodesBean> finlDatas = new ArrayList<>();
        int size = days.size();
        for (int i = 0; i < size; i++) {
            PlanDaysBean bean = days.get(i);
            PlanNodesBean todayNoForget = new PlanNodesBean();
            todayNoForget.setEntry_name(App.getStringRes(R.string.noforget));
            todayNoForget.setTips(bean.getMemo());
            todayNoForget.setCandidate(false);
            List<PlanNodesBean> nodes = bean.getPlan_nodes();
            String title = "Day" + (i + 1) + " " + nodes.get(0).getDestination().getName_zh_cn();
            todayNoForget.setFirstTitle(title);
            todayNoForget.setUser_entry(false);
            todayNoForget.setPosition(-1);
            finlDatas.add(todayNoForget);
            menuTitle.add(title);
            int nodesSize = nodes.size();
            List<String> menuItemName = new ArrayList<>();
            for (int j = 0; j < nodesSize; j++) {
                PlanNodesBean nodesBean = nodes.get(j);
                String entry_name;
                if (nodesBean.isCandidate()) {
                    entry_name = "备选：" + nodesBean.getEntry_name();
                } else {
                    entry_name = "第" + (j + 1) + "站：" + nodesBean.getEntry_name();
                }
                nodesBean.setFirstTitle(title);
                nodesBean.setEntry_name(entry_name);
                menuItemName.add(entry_name);
                finlDatas.add(nodesBean);
            }
            menuItem.add(menuItemName);
        }

        mView.setMenu(menuTitle, menuItem);
        mView.setData(finlDatas);
    }
}
