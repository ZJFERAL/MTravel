package com.mcy.mtravel.presenter;

import com.mcy.mtravel.entity.index.IndexBean;
import com.mcy.mtravel.model.GetTokenModel;
import com.mcy.mtravel.model.NewsModel;
import com.mcy.mtravel.model.impl.GetTokenModelImpl;
import com.mcy.mtravel.model.impl.NewsModelImpl;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.utils.SPUtils;
import com.mcy.mtravel.view.impl.NewsView;
import com.zjf.core.impl.OnAsyncModelListener;
import com.zjf.core.presenter.Presenter;

/**
 * Created by jifengZhao on 2017/4/14.
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
                if (type == FinalParams.ERROR_TOEKN) {
                    if (mTokenModel == null) {
                        mTokenModel = new GetTokenModel();
                    }
                    getToken(1);
                } else {
                    mView.onFailure(msg, type);
                }
            }

            @Override
            public void onSuccess(IndexBean msg) {
                mView.onRefreshData(msg.getData().getFeeds().getList(), msg.getData().getSlide());
            }
        });
    }

    private void getToken(final int which) {
        mTokenModel.getData(new OnAsyncModelListener<String>() {
            @Override
            public void onFailure(String msg, int type) {
                mView.onFailure(msg, type);
            }

            @Override
            public void onSuccess(String msg) {
                SPUtils spUtils = new SPUtils();
                spUtils.saveToken(msg);
                if (which == 1) {
                    onFlushData();
                } else {
                    getMoreData();
                }
            }
        });
    }

    public void getMoreData() {
        mNewsModel.getData(new OnAsyncModelListener<IndexBean>() {
            @Override
            public void onFailure(String msg, int type) {
                if (type == FinalParams.ERROR_TOEKN) {
                    if (mTokenModel == null) {
                        mTokenModel = new GetTokenModel();
                    }
                    getToken(2);
                } else {
                    mView.showSnakBar(msg, type);
                }
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
        if (mTokenModel != null) {
            mTokenModel.cancel();
        }
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        mTokenModel = null;
        mNewsModel = null;
    }
}
