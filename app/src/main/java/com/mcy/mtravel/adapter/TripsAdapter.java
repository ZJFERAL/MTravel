package com.mcy.mtravel.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.TripsBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/18.
 */

public class TripsAdapter extends CRecyclerViewAdapter<TripsBean> {

    private int width;

    public TripsAdapter(Context context, List<TripsBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        width = DeviceUtils.getDeviceScreenWidth(context);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, TripsBean item, int position) {
        holder.setImageByUrl(R.id.img_cover, item.getFront_cover_photo_url(), R.drawable.weit_place)
                .setText(R.id.txt_title, item.getName())
                .setText(R.id.txt_time, item.getStart_date() + "/" + item.getDays() + "天," + item.getPhotos_count() + "图")
                .setImageByUrl(R.id.img_user, item.getUser().getImage(), R.drawable.weit_place);
        ImageView cover = holder.getView(R.id.img_cover);
        cover.setLayoutParams(new RelativeLayout.LayoutParams(width, (int) (width / 1.7)));
        ImageView userHead = holder.getView(R.id.img_user);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width / 9, width / 9);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        userHead.setLayoutParams(params);
    }
}
