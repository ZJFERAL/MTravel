package com.mcy.mtravel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.NotesBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-04-20 下午 10:49
 */

public class TripsNoteAdapter extends CRecyclerViewAdapter<NotesBean> {

    private String mLastName = "";
    private int mWidth;

    public TripsNoteAdapter(Context context, List<NotesBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(context);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, NotesBean item, int position) {
        TextView txtLocation = holder.getView(R.id.txt_location);
        txtLocation.setOnClickListener(null);
        txtLocation.setTextColor(mContext.getResources().getColor(R.color.colorSimpleDark));

        txtLocation.setText(item.getEntry_name());
        if (!item.isUser_entry()) {
            txtLocation.setTextColor(Color.BLUE);
            txtLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        View layout = holder.getView(R.id.layout_title);
        layout.setVisibility(View.GONE);
        String name = item.getEntry_name();
        if (!name.equals(mLastName)) {
            layout.setVisibility(View.VISIBLE);
            mLastName = name;
            holder.setText(R.id.txt_title, name);
        }

        TextView txtContent = holder.getView(R.id.txt_content);
        txtContent.setVisibility(View.GONE);
        String description = item.getDescription();
        if (!TextUtils.isEmpty(description)) {
            txtContent.setVisibility(View.VISIBLE);
            txtContent.setText(description);
        }

        ImageView imgNote = holder.getView(R.id.img_note);
        imgNote.setVisibility(View.GONE);
        if (item.getPhoto() != null) {
            imgNote.setVisibility(View.VISIBLE);
            imgNote.setLayoutParams(new LinearLayout.LayoutParams(mWidth, mWidth / 2));
            Glide.with(mContext).load(item.getPhoto().getUrl()).placeholder(R.drawable.weit_place)
                    .into(imgNote);
        }
    }
}
