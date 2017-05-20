package com.zjf.core.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by machengyuan on 2017/4/14.
 */

public class ImageViewPagerAdapter extends PagerAdapter {

    private List<ImageView> mImageList;

    public ImageViewPagerAdapter(List<ImageView> imageList) {
        mImageList = imageList;
    }

    @Override
    public int getCount() {
        return mImageList != null ? mImageList.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View ret = null;
        ret = mImageList.get(position);
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
