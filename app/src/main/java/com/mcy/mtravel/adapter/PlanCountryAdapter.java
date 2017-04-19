package com.mcy.mtravel.adapter;

import android.content.Context;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.TargetPlaceBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/19.
 */

public class PlanCountryAdapter extends CRecyclerViewAdapter<TargetPlaceBean.DestinationsBean> {

    public PlanCountryAdapter(Context context, List<TargetPlaceBean.DestinationsBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, TargetPlaceBean.DestinationsBean item, int position) {
        holder.setImageByUrl(R.id.img_bg, item.getImage_url(), R.drawable.weit_place)
                .setText(R.id.txt_country_name, item.getName_zh_cn() + "\n" + item.getName_en())
                .setText(R.id.txt_poi_num, item.getPoi_count() + "");
    }
}
