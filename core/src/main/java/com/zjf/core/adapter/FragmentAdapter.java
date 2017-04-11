package com.zjf.core.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-05 下午 3:05
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mData;

    public FragmentAdapter(FragmentManager fm, List<Fragment> mData) {
        super(fm);
        this.mData = mData;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }
}
