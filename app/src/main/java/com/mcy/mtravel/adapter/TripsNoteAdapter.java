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
import com.mcy.mtravel.entity.PhotoBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-04-20 下午 10:49
 */

public class TripsNoteAdapter extends CRecyclerViewAdapter<NotesBean> {

    private int mWidth;

    public TripsNoteAdapter(Context context, List<NotesBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(context);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, NotesBean item, int position) {
        //底部位置
        TextView txtLocation = holder.getView(R.id.txt_location);
        txtLocation.setVisibility(View.GONE);
        txtLocation.setOnClickListener(null);
        txtLocation.setTextColor(mContext.getResources().getColor(R.color.colorLightDark));
        String entry_name = item.getEntry_name();
        if (!TextUtils.isEmpty(entry_name)) {
            txtLocation.setVisibility(View.VISIBLE);
            txtLocation.setText(entry_name);
            if (!item.isUser_entry()) {
                txtLocation.setTextColor(Color.BLUE);
                txtLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }


        //标题
        View layout = holder.getView(R.id.layout_title);
        layout.setVisibility(View.GONE);
        String lastName = "";
        if (position != 0) {
            lastName = mData.get(position - 1).getEntry_name();
        }
        if ((!TextUtils.isEmpty(entry_name)) && (!entry_name.equals(lastName))) {
            layout.setVisibility(View.VISIBLE);
            holder.setText(R.id.txt_title, entry_name);
        }

        //文字内容
        TextView txtContent = holder.getView(R.id.txt_content);
        txtContent.setVisibility(View.GONE);
        String description = item.getDescription();
        if (!TextUtils.isEmpty(description)) {
            txtContent.setVisibility(View.VISIBLE);
            txtContent.setText(description);
        }

        //图片内容
        ImageView imgNote = holder.getView(R.id.img_note);
        imgNote.setVisibility(View.GONE);
        PhotoBean photo = item.getPhoto();
        if (photo != null) {
            imgNote.setVisibility(View.VISIBLE);
            int width = photo.getImage_width();
            int height = photo.getImage_height();
            float scale = width * 1f / mWidth;
            float realHeight = height / scale;
            imgNote.setLayoutParams(new LinearLayout.LayoutParams(mWidth, (int) realHeight));
            Glide.with(mContext).load(photo.getUrl()).placeholder(R.drawable.weit_place)
                    .into(imgNote);
        }
    }
}
