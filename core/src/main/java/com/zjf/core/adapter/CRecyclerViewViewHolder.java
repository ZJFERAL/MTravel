package com.zjf.core.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * @author :ZJF
 * @version : 2016-12-02 上午 11:11
 */

public class CRecyclerViewViewHolder extends RecyclerView.ViewHolder {

    private int mtype;
    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViews;

    private CRecyclerViewViewHolder(Context context, View itemView, int type) {
        super(itemView);
        this.mtype = type;
        this.mContext = context;
        this.mConvertView = itemView;
        this.mViews = new SparseArray<View>();
    }

    public int getType() {
        return mtype;
    }

    public static CRecyclerViewViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId, int type) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        CRecyclerViewViewHolder holder = new CRecyclerViewViewHolder(context, itemView, type);
        return holder;
    }

    public static CRecyclerViewViewHolder createViewHolder(Context context,  View itemView, int type) {
        CRecyclerViewViewHolder holder = new CRecyclerViewViewHolder(context, itemView, type);
        return holder;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public CRecyclerViewViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public CRecyclerViewViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public CRecyclerViewViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public CRecyclerViewViewHolder setImageByUrl(int viewId, String url) {
        ImageView imageView = getView(viewId);
        Glide.with(mContext).load(url).into(imageView);
        return this;
    }

    public CRecyclerViewViewHolder setImageByUrl(int viewId, String url, int placeholder) {
        ImageView imageView = getView(viewId);
        Glide.with(mContext).load(url).placeholder(placeholder).into(imageView);
        return this;
    }

    public CRecyclerViewViewHolder setImageByUrl(int viewId, String url, int width, int height) {
        ImageView imageView = getView(viewId);
        Glide.with(mContext).load(url).override(width, height).into(imageView);
        return this;
    }

    public CRecyclerViewViewHolder setImageByUrl(int viewId, String url, int width, int height, int placeholder) {
        ImageView imageView = getView(viewId);
        Glide.with(mContext).load(url).override(width, height).placeholder(placeholder).into(imageView);
        return this;
    }

    public CRecyclerViewViewHolder setOnclickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        view.setOnClickListener(onClickListener);
        return this;
    }
}
