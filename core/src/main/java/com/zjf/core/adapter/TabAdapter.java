package com.zjf.core.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-12-19 下午 7:30
 */

public class TabAdapter extends FragmentAdapter{
    private String[] title;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments, String[] title) {
        super(fm, fragments);
        this.title = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
