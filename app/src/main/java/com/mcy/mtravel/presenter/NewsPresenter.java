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
    protected void onViewCreated() {
        onFlushData();
    }

    public void onFlushData() {
        mNewsModel.getRefreshData(new OnAsyncModelListener<IndexBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.showSnakBar(msg, type);
            }

            @Override
            public void onSuccess(IndexBean msg) {
                mView.onRefreshData(msg.getData().getFeeds().getList(), msg.getData().getSlide());
            }
        });
    }

    public void getMoreData() {
        mNewsModel.getData(new OnAsyncModelListener<IndexBean>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.showSnakBar(msg, type);
            }

            @Override
            public void onSuccess(IndexBean msg) {
                mView.onGetMoreData(msg.getData().getFeeds().getList());
            }
        });

    }

    @Override
    public void onViewDeached() {
        super.onViewDeached();
        if (mNewsModel != null) {
            mNewsModel.cancel();
        }
    }
}
