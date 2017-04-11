package com.zjf.core.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * @author :ZJF
 * @version : 2016-11-25 下午 3:10
 */

public class CAbsViewViewHolder {
    private final SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    private int mType;

    private CAbsViewViewHolder(Context context, int layoutId, ViewGroup parent, int type) {
        this.mViews = new SparseArray<>();
        this.mType = type;
        this.mContext = context;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static CAbsViewViewHolder creatViewHolder(View convertView, Context context, int layoutId,
                                         ViewGroup parent, int type) {
        if (convertView == null) {
            return new CAbsViewViewHolder(context, layoutId, parent, type);
        }
        return (CAbsViewViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    public Context getContext() {
        return mContext;
    }

    public int getType() {
        return mType;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public CAbsViewViewHolder setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public CAbsViewViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public CAbsViewViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public CAbsViewViewHolder setImageByUrl(int viewId, String url) {
        ImageView imageView = getView(viewId);
        Glide.with(mContext).load(url).into(imageView);
        return this;
    }

    public CAbsViewViewHolder setOnclickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        view.setOnClickListener(onClickListener);
        return this;
    }
}
