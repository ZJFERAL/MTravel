package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.webview)
    WebView mWebview;
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
                if (url.equals(mSource)) {
                    view.loadUrl(url);
                }
                return true;
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
