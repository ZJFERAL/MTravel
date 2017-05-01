package com.zjf.core.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2016-11-25 下午 3:47
 */

public abstract class CAbsViewAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mData;
    protected int[] mItemLayoutIds;

    public CAbsViewAdapter(Context context, List<T> data, int... itemLayoutIds) {
        this.mContext = context;
        this.mData = data;
        this.mItemLayoutIds = itemLayoutIds;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return mItemLayoutIds.length;
    }

    @Override
    public int getItemViewType(int position) {
        return getConverViewType(position);
    }

    public int getConverViewType(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getConverViewType(position);
        CAbsViewViewHolder holder = getViewHolder(convertView, parent, type);
        setItemView(holder, getItem(position),position);
        return holder.getConvertView();
    }

    protected abstract void setItemView(CAbsViewViewHolder holder, T item, int position);

    private CAbsViewViewHolder getViewHolder(View convertView, ViewGroup parent, int type) {
        return CAbsViewViewHolder.creatViewHolder(convertView, mContext, mItemLayoutIds[type], parent, type);
    }

    public void flushData(List<T> data) {
        this.mData.clear();
        this.addData(data);
    }

    public void addData(List<T> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }
}
