package com.mcy.mtravel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.base.BaseActivity;
import com.zjf.core.utils.LogUtils;
import com.zjf.core.utils.NetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.txt_progress_num)
    TextView mTxtProgressNum;

    private String mSource;
    private static final String APP_CACAHE_DIRNAME = "webviewCace";

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
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        if (NetUtils.isNetworkReachable(mContext)) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        } else {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式
        }

        // 开启 DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);

        //开启 database storage API 功能
        mWebView.getSettings().setDatabaseEnabled(true);

        //String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        String cacheDirPath = getCacheDir().getAbsolutePath() + APP_CACAHE_DIRNAME;

        Log.i(TAG, "cacheDirPath=" + cacheDirPath);

        //设置数据库缓存路径
        mWebView.getSettings().setDatabasePath(cacheDirPath);

        //设置  Application Caches 缓存目录
        mWebView.getSettings().setAppCachePath(cacheDirPath);

        //开启 Application Caches 功能
        mWebView.getSettings().setAppCacheEnabled(true);
        //设置Web视图
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("Path", url);
                if (url.startsWith("http://m") && (!url.contains("authors"))) {
                    view.loadUrl(url);
                }
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 80) {
                    mEmptyView.setVisibility(View.GONE);
                } else {
                    mTxtProgressNum.setText(" " + getString(R.string.no_data) + newProgress + "%");
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mToolbar.setTitle(title.replace("穷游锦囊", "漫途"));
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        });
        //加载需要显示的网页
        mWebView.loadUrl(mSource);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }
}
