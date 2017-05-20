package com.mcy.mtravel.presenter;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.index.TargetPlaceBean;
import com.mcy.mtravel.model.PlanModel;
import com.mcy.mtravel.model.impl.PlanModelImpl;
import com.mcy.mtravel.view.impl.PlanView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;
import com.zjf.core.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machengyuan on 2017/4/14.
 */

public class PlanPresenter extends Presenter<PlanView> {

    private PlanModelImpl mPlanModel;
    private List<String> titles;
    private List<TargetPlaceBean> mBeanList;

    public PlanPresenter() {
        mPlanModel = new PlanModel();
        titles = new ArrayList<>();
        mBeanList = new ArrayList<>();
    }

    @Override
    protected void onViewCreated() {
        getTitles();
    }

    public void getTitles() {
        mPlanModel.getData(new OnAsyncModelListener<List<TargetPlaceBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
                LogUtils.e("PlanPresenter", "getTitles error");
            }

            @Override
            public void onSuccess(List<TargetPlaceBean> msg) {
                mBeanList.clear();
                mBeanList.addAll(msg);
                int size = msg.size();
                titles.clear();
                for (int i = 0; i < size; i++) {
                    titles.add(getStrByInt(msg.get(i).getCategory()));
                }
                mView.onLeftItem(titles);
                getRightData(0);
            }
        });
    }

    public void getRightData(int position) {
        mView.onRightItem(mBeanList.get(position).getDestinations());
    }

    private String getStrByInt(String anInt) {
        int parseInt = Integer.parseInt(anInt);
        String string = "";
        switch (parseInt) {
            case 1:
                string = App.getStringRes(R.string.asian);
                break;
            case 2:
                string = App.getStringRes(R.string.europe);
                break;
            case 3:
                string = App.getStringRes(R.string.us);
                break;
            case 99:
                string = App.getStringRes(R.string.in_gat);
                break;
            case 999:
                string = App.getStringRes(R.string.in_dl);
                break;
        }
        return string;
    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        mPlanModel.cancel();
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mPlanModel = null;
    }
}
