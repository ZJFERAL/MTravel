package com.mcy.mtravel.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tip.TipTripsBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public class TipTripsListAdapter extends CRecyclerViewAdapter<TipTripsBean> {

    private int mWidth;

    public TipTripsListAdapter(Context context, List<TipTripsBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(mContext);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, TipTripsBean item, int position) {
        holder.setText(R.id.txt_title, item.getName())
                .setText(R.id.txt_date, item.getPlan_days_count() + "天")
                .setText(R.id.txt_num, item.getPlan_nodes_count() + "个旅行地")
                .setText(R.id.txt_content, item.getDescription())
                .setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place);
        ImageView view = holder.getView(R.id.img_cover);
        view.setLayoutParams(new RelativeLayout.LayoutParams(mWidth, (int) (mWidth / 1.9)));
    }
}
