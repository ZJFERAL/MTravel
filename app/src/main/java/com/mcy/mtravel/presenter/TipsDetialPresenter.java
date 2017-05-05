package com.mcy.mtravel.presenter;

import android.text.TextUtils;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tipwiki.ChildrenBean;
import com.mcy.mtravel.entity.tipwiki.PagesBean;
import com.mcy.mtravel.entity.tipwiki.StrategyDetialBean;
import com.mcy.mtravel.model.TipModel;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.impl.TipsDetialView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-04-30 下午 10:19
 */

public class TipsDetialPresenter extends Presenter<TipsDetialView> {

    private String mID;
    private TipModel mModelImp;
    private int mGroupPos = -1, mChildPos = -1;
    private String mChildTitle;

    public TipsDetialPresenter(String ID) {
        mID = ID;
        mModelImp = TipModel.getInstance(mID);
    }

    public TipsDetialPresenter(String ID, int groupPos, int childPos) {
        this(ID);
        mChildPos = childPos;
        mGroupPos = groupPos;
    }

    public TipsDetialPresenter(String ID, String childTitle) {
        this(ID);
        mChildTitle = dutyTitle(childTitle);
    }

    private String dutyTitle(String title) {
        String result = null;
        if (!TextUtils.isEmpty(title)) {
            String[] split = title.split("[-]");
            if (split.length >= 2) {
                result = split[1].trim();
            }
        }
        return result;
    }

    public void setChildTitle(String childTitle) {
        mChildTitle = dutyTitle(childTitle);
    }

    public void setGroupPos(int groupPos) {
        mGroupPos = groupPos;
    }

    public void setChildPos(int childPos) {
        mChildPos = childPos;
    }

    @Override
    protected void onViewCreated() {
        mModelImp.getData(new OnAsyncModelListener<List<StrategyDetialBean>>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(List<StrategyDetialBean> msg) {
                if (mChildPos != -1 && mGroupPos != -1) {
                    PagesBean bean = msg.get(mGroupPos).getPages().get(mChildPos);
                    mView.onGetData(bean.getChildren());
                } else if (!TextUtils.isEmpty(mChildTitle)) {
                    List<ChildrenBean> list = null;
                    int size = msg.size();
                    for (int i = 0; i < size; i++) {
                        List<PagesBean> pages = msg.get(i).getPages();
                        int pagesSize = pages.size();
                        for (int j = 0; j < pagesSize; j++) {
                            String title = pages.get(j).getTitle();
                            if (mChildTitle.equals(title)) {
                                if (list == null) {
                                    list = new ArrayList<>();
                                }
                                list.addAll(msg.get(i).getPages().get(j).getChildren());
                                break;
                            }
                            List<ChildrenBean> children = pages.get(j).getChildren();
                            int sizeChild = children.size();
                            for (int k = 0; k < sizeChild; k++) {
                                ChildrenBean bean = children.get(k);
                                if (mChildTitle.equals(bean.getTitle())) {
                                    if (list == null) {
                                        list = new ArrayList<>();
                                    }
                                    list.add(bean);
                                }
                            }
                        }
                    }
                    if (list == null) {
                        mView.onFailure(App.getStringRes(R.string.error_net), FinalParams.ERROR_INFO);
                    } else {
                        mView.onGetData(list);
                    }
                }
            }
        });
    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        if (mModelImp != null) {
            mModelImp.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mModelImp = null;
    }
}
