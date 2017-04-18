package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.BaseActivity;
import com.zjf.core.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.txt_progress_num)
    TextView mTxtProgressNum;

    private String mSource;

    @Override
    public void initVariables() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        mSource = data.getString("path");
        //设置WebView属性，能够执行Javascript脚本
        mWebview.getSettings().setJavaScriptEnabled(true);
        //设置Web视图
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("Path", url);
                if (url.startsWith("http://m") && (!url.contains("authors"))) {
                    view.loadUrl(url);
                }
                return true;
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 95) {
                    mEmptyView.setVisibility(View.GONE);
                } else {
                    mTxtProgressNum.setText(" " + getString(R.string.no_data) + newProgress + "%");
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mToolbar.setTitle(title);
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        });
        //加载需要显示的网页
        mWebview.loadUrl(mSource);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            finish();
        }
    }
}
