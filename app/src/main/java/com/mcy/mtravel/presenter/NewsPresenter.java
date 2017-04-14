package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.IndexBean;
import com.mcy.mtravel.model.NewsModel;
import com.mcy.mtravel.model.impl.GetTokenModelImpl;
import com.mcy.mtravel.model.impl.NewsModelImpl;
import com.mcy.mtravel.view.impl.NewsView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

/**
 * Created by zhaojifeng on 2017/4/14.
 */

public class NewsPresenter extends Presenter<NewsView> {

    private GetTokenModelImpl mTokenModel;
    private NewsModelImpl mNewsModel;

    public NewsPresenter() {
        mNewsModel = new NewsModel();
    }

    @Override
    protected void onViewStart() {
        mNewsModel.getData(new OnAsyncModelListener<IndexBean>() {
            @Override
            public void onFailure(String msg, int type) {
                
            }

            @Override
            public void onSuccess(IndexBean msg) {

            }
        });
    }
}
