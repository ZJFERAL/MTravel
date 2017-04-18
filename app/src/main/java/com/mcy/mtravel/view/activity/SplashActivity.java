package com.mcy.mtravel.view.activity;

import android.animation.Animator;
import android.support.design.widget.CoordinatorLayout;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mcy.mtravel.R;
import com.mcy.mtravel.base.MVPActivity;
import com.mcy.mtravel.presenter.SplashPresenter;
import com.mcy.mtravel.view.impl.SplashView;
import com.zjf.core.utils.SnackBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends MVPActivity<SplashPresenter> implements SplashView {

    @BindView(R.id.coor_bg)
    CoordinatorLayout mCoorBG;
    @BindView(R.id.img_splash_bg)
    ImageView mImgSplashBg;
    @BindView(R.id.logo)
    TextView mLogo;

    private boolean isFirst = false;
    private boolean isAnimDone = false;
    private boolean isNetWorkDone = false;

    @Override
    public void initVariables() {
        super.initVariables();
        isFirst = mSPUtils.getFirstFlag();
    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Glide.with(mContext).load(R.drawable.splash_bg).listener(new RequestListener<Integer, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                mImgSplashBg.setImageDrawable(resource);
                onUIReady();
                return true;
            }
        }).into(mImgSplashBg);

    }

    private void onUIReady() {
        mImgSplashBg.setScaleX(1.5f);
        mImgSplashBg.setScaleY(1.3f);
        mImgSplashBg.setAlpha(0.2f);
        mImgSplashBg.animate()
                .alpha(1f)
                .scaleX(1.9f)
                .scaleY(1.6f)
                .setDuration(2000)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startLogo();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    private void startLogo() {
        mLogo.animate()
                .translationY(-200)
                .setDuration(1000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isAnimDone = true;
                        startAPP();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    @Override
    public void setListener() {

    }


    @Override
    public SplashPresenter create() {
        return new SplashPresenter();
    }

    @Override
    public void showSnakBar(String msg, int type) {
        SnackBarUtils.ShortSnackbar(mCoorBG, msg, type).show();
    }

    public void startAPP() {
        if (isNetWorkDone && isAnimDone) {
            if (isFirst) {
                jumpTo(this, GuideActivity.class, true);
            } else {
                jumpTo(this, MainActivity.class, true);
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public void NetWorkDone(String token) {
        isNetWorkDone = true;
        mSPUtils.saveToken(token);
        mSPUtils.changeFirstFlag();
        startAPP();
    }

    @Override
    public void exit() {
        SplashActivity.this.finish();
    }
}
