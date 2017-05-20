package com.mcy.mtravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.special.ArticleSectionsBean;
import com.mcy.mtravel.entity.special.AttractionBean;
import com.mcy.mtravel.entity.special.NoteBean;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.activity.TripsNoteActivity;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-05-20 下午 2:46
 */

public class SpecialDetialAdapter extends CRecyclerViewAdapter<ArticleSectionsBean> {

    private int mWidth;
    private List<AttractionBean> mAttractionBeen;

    public SpecialDetialAdapter(Context context, List<ArticleSectionsBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(context);
    }

    public void setAttractionBeen(List<AttractionBean> attractionBeen) {
        mAttractionBeen = attractionBeen;
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, ArticleSectionsBean item, int position) {
        String title = item.getTitle();
        TextView titleView = holder.getView(R.id.txt_item_title);
        if (TextUtils.isEmpty(title)) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
            titleView.setText(title);
        }

        String description = item.getDescription();
        TextView contenView = holder.getView(R.id.txt_item_content);
        if (TextUtils.isEmpty(description)) {
            contenView.setVisibility(View.GONE);
        } else {
            contenView.setVisibility(View.VISIBLE);
            contenView.setText(description);
        }

        String url = item.getImage_url();
        RelativeLayout layoutImage = holder.getView(R.id.lay_imag);
        if (TextUtils.isEmpty(url)) {
            layoutImage.setVisibility(View.GONE);
        } else {
            layoutImage.setVisibility(View.VISIBLE);
            ImageView coverView = holder.getView(R.id.img_item_cover);
            int width = item.getImage_width();
            int height = item.getImage_height();
            float scale = mWidth * 1f / width;
            int realHeight = (int) (height * scale);
            coverView.setLayoutParams(new RelativeLayout.LayoutParams(mWidth, realHeight));
            NoteBean note = item.getNote();
            holder.setImageByUrl(R.id.img_item_cover, url, R.drawable.weit_place);
            if (note != null) {
                String name = note.getUser_name();
                String trip_name = note.getTrip_name();
                final int noteId = note.getId();
                holder.setText(R.id.txt_item_pic_from_author, name
                        + " 的 " + trip_name + "游记")
                        .setOnclickListener(R.id.lay_imag, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString(FinalParams.TRIPS_NOTE_ID, noteId + "");
                                Intent intent = new Intent(mContext, TripsNoteActivity.class);
                                intent.putExtra("data", bundle);
                                mContext.startActivity(intent);
                            }
                        });
            }
        }

        AttractionBean attraction = item.getAttraction();
        TextView locationView = holder.getView(R.id.txt_item_locatiuon);

        if (attraction == null) {
            locationView.setVisibility(View.GONE);
        } else {
            locationView.setVisibility(View.VISIBLE);
            locationView.setText(attraction.getName());
            locationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        View view = holder.getView(R.id.lay_bottom);
        if (position == getRealCount() - 1) {
            view.setVisibility(View.VISIBLE);
            view.setLayoutParams(new LinearLayout.LayoutParams(mWidth, (int) (mWidth / 2.5)));
            holder.setText(R.id.txt_about_location,
                    App.getStringRes(R.string.about_loaction)
                            + ":" + mAttractionBeen.size());
            RecyclerView recyclerView = holder.getView(R.id.item_recy);
            SpecialLocaionAdapter mAdapter = new SpecialLocaionAdapter(mContext, mAttractionBeen, R.layout.item_special_adapter_location);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(mAdapter);
        } else {
            view.setVisibility(View.GONE);
        }

    }
}
