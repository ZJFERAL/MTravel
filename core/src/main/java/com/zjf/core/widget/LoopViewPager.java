package com.zjf.core.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zjf.core.utils.SizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jifengZhao on 2017/4/19.
 */

public class LoopViewPager extends FrameLayout {

    public interface OnItemClickListener {
        void onItemClick(ViewPager viewPager, View view, int position);
    }

    private int mSize;
    private int mWidth;
    private int mHeight;
    private ViewPager mViewPager;
    private List<FrameLayout> mViewList;
    private List<TextView> mTextViewList;
    private CircleIndicator mCircleIndicator;
    private OnItemClickListener mItemClickListener;
    private int postTime = 3000;
    private boolean isAutoStart = false;
    private CircleIndicator.Gravity mGravity = CircleIndicator.Gravity.CENTER;

    public LoopViewPager(@NonNull Context context) {
        this(context, null);
    }

    public LoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mViewPager = new ViewPager(context);
        mWidth = context.getResources().getDisplayMetrics().widthPixels;
        mHeight = mWidth / 2;
        mViewPager.setLayoutParams(new LayoutParams(mWidth, mHeight));
        addView(mViewPager);
    }

    public void setAutoStart() {
        isAutoStart = true;
    }

    public int getPostTime() {
        return postTime;
    }

    public void setPostTime(int postTime) {
        this.postTime = postTime;
    }

    public void setViewPagerWidthHeight(int width, int height) {
        mWidth = width;
        mHeight = height;
        mViewPager.setLayoutParams(new LayoutParams(width, height));
    }

    public void setUrls(final List<String> urls) {
        mSize = urls.size();
        if (mViewPager != null) {
            makeView(urls);
            mViewPager.setAdapter(new LoopImageViewAdapter());
            if (urls.size() >= 2) {
                mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                        if (positionOffset == 0.0) {
                            if (position == 0) {
                                mViewPager.setCurrentItem(urls.size(), false);
                            } else if (position == urls.size() + 1) {
                                mViewPager.setCurrentItem(1, false);
                            }
                        }
                    }
                });
            }
            if (isAutoStart) {
                mHandler.postDelayed(mRunnable, postTime);
            }
        }
    }


    private void makeView(List<String> urls) {
        if (mViewList == null) {
            mViewList = new ArrayList<>();
        }
        List<ImageView> imageList = new ArrayList<>();
        int size = urls.size();
        if (size >= 2) {
            String lastUrl = urls.get(size - 1);
            ImageView realFirstImg = new ImageView(getContext());
            realFirstImg.setLayoutParams(new FrameLayout.LayoutParams(mWidth, mHeight));
            realFirstImg.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(lastUrl).override(mWidth, mHeight).into(realFirstImg);
            imageList.add(realFirstImg);
        }
        for (int i = 0; i < size; i++) {
            String url = urls.get(i);
            ImageView img = new ImageView(getContext());
            img.setLayoutParams(new FrameLayout.LayoutParams(mWidth, mHeight));
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            final int finalI = i;
            img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(mViewPager, v, finalI);
                }
            });
            Glide.with(getContext()).load(url).override(mWidth, mHeight).into(img);
            imageList.add(img);
        }
        if (size >= 2) {
            String firstUrl = urls.get(0);
            ImageView realLastImg = new ImageView(getContext());
            realLastImg.setLayoutParams(new FrameLayout.LayoutParams(mWidth, mHeight));
            realLastImg.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(firstUrl).override(mWidth, mHeight).into(realLastImg);
            imageList.add(realLastImg);
        }
        for (int i = 0; i < imageList.size(); i++) {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new ViewPager.LayoutParams());
            frameLayout.addView(imageList.get(i));
            mViewList.add(frameLayout);
        }

    }

    public void setOnPagerChangListener(ViewPager.OnPageChangeListener listener) {
        mViewPager.addOnPageChangeListener(listener);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setCurrentItem(int item) {
        mViewPager.setCurrentItem(item);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        mViewPager.setCurrentItem(item, smoothScroll);
    }


    public void setPointLayout(CircleIndicator.Gravity gravity) {
        if (gravity != null) {
            this.mGravity = gravity;
        }
        if (mSize == 0) {
            throw new IllegalStateException("you must call the method \"setUrls\" first");
        }
        makePoint(mSize);
    }

    private void makePoint(int count) {
        if (count <= 1) {
            return;
        }
        mCircleIndicator = new LinePoint(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, SizeUtils.dp2px(40f, getContext()));
        params.gravity = Gravity.BOTTOM;
        params.setMargins(0, 0, SizeUtils.dp2px(5f, getContext()), SizeUtils.dp2px(5f, getContext()));
        mCircleIndicator.setLayoutParams(params);
        mCircleIndicator.setIndicatorRadius(SizeUtils.dp2px(4, getContext()));//圆的大小
        mCircleIndicator.setIndicatorMargin(SizeUtils.dp2px(7, getContext()));//间隔
        mCircleIndicator.setIndicatorBackground(Color.parseColor("#d4bebcbc"));
        mCircleIndicator.setIndicatorSelectedBackground(Color.parseColor("#e6ffffff"));
        mCircleIndicator.setIndicatorLayoutGravity(mGravity);
        mCircleIndicator.setIndicatorMode(CircleIndicator.Mode.OUTSIDE);
        mCircleIndicator.setViewPager(mViewPager, count);
        addView(mCircleIndicator);
    }

    public void removePointLayout() {
        if (mCircleIndicator != null) {
            try {
                removeView(mCircleIndicator);
            } catch (Exception e) {
            }
            mCircleIndicator = null;
        }
    }

    public void setTextLayout(String[] strings) {
        if (mViewList == null || mViewList.size() == 0) {
            throw new IllegalStateException("you must call the method \"setUrls\" first");
        }
        if (strings.length < mSize) {
            throw new IllegalStateException("too sort!");
        }
        makeTextView(strings);
    }

    private void makeTextView(String[] strings) {
        for (int i = 0; i < mSize; i++) {
            TextView textView = new TextView(getContext());
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.BOTTOM;
            textView.setLayoutParams(params);
            textView.setBackgroundColor(Color.parseColor("#b78b8a8a"));
            textView.setText(strings[i]);
            textView.setTextColor(Color.WHITE);
            mViewList.get(i).addView(textView);
        }
    }

    public void removeTextLayout() {
        if (mTextViewList != null && mTextViewList.size() != 0) {
            try {
                for (int i = 0; i < mViewList.size(); i++) {
                    mViewList.get(i).removeView(mTextViewList.get(i));
                }
            } catch (Exception e) {
            }
            mTextViewList = null;
        }
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            mHandler.postDelayed(this, postTime);
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && isAutoStart) {
            mHandler.postDelayed(mRunnable, postTime);
        } else {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mHandler.removeCallbacks(mRunnable);
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (isAutoStart) {
                mHandler.postDelayed(mRunnable, postTime);
            }
        }
        return super.dispatchTouchEvent(ev);

    }

    class LoopImageViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList != null ? mViewList.size() : 0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View ret = null;
            ret = mViewList.get(position);
            container.addView(ret);
            return ret;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object instanceof View) {
                View view = (View) object;
                container.removeView(view);
            }
        }
    }


    class LinePoint extends CircleIndicator {

        public LinePoint(Context context) {
            super(context);
        }

        public LinePoint(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public LinePoint(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void setUpListener() {
            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    if (mIndicatorMode != Mode.SOLO) {
                        if (position > 0 && position < mSize) {
                            trigger(position - 1, positionOffset);
                        }
                    }

                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    if (mIndicatorMode == Mode.SOLO) {
                        if (position > 0 && position <= mSize) {
                            trigger(position - 1, 0);
                        }
                    }
                    if (position == 0) {
                        trigger(mSize - 1, 0);
                    } else if (position == mSize + 1) {
                        trigger(0, 0);
                    }
                }
            });
        }
    }

}
