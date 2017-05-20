package com.mcy.mtravel.presenter;

import android.text.TextUtils;

import com.mcy.mtravel.entity.special.ArticleSectionsBean;
import com.mcy.mtravel.entity.special.AttractionBean;
import com.mcy.mtravel.entity.special.SpecialBean;
import com.mcy.mtravel.model.SpecialDeticalModel;
import com.mcy.mtravel.view.impl.SpecialDetialView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter_temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-05-20 下午 3:33
 */

public class SpecialDetialPresenter extends Presenter_temp<SpecialDetialView, SpecialDeticalModel> {

    private String mID;

    public SpecialDetialPresenter(String mID) {
        this.mID = mID;
        mModel = new SpecialDeticalModel(mID);
    }

    @Override
    protected void onViewCreated() {
        mModel.getData(new OnAsyncModelListener<SpecialBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(SpecialBean msg) {
                List<AttractionBean> mAttractionBeen = new ArrayList<AttractionBean>();
                int size = msg.getArticle_sections().size();
                List<ArticleSectionsBean> sections = msg.getArticle_sections();
                for (int i = 0; i < size; i++) {
                    ArticleSectionsBean bean = sections.get(i);
                    String title = bean.getTitle();
                    if (TextUtils.isEmpty(title)) {
                        if (i > 0) {
                            bean.setTitle(sections.get(i - 1).getTitle());
                        }
                    }
                    AttractionBean attraction = bean.getAttraction();
                    if (attraction != null) {
                        mAttractionBeen.add(attraction);
                    }
                }
                mView.getData(msg.getArticle_sections(), mAttractionBeen);
            }
        });
    }
}
