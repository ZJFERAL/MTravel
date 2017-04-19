package com.mcy.mtravel.adapter;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.TargetPlaceBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/19.
 */

public class PlanCountryAdapter extends CRecyclerViewAdapter<TargetPlaceBean.DestinationsBean> {

    private int widthPixels;

    public PlanCountryAdapter(Context context, List<TargetPlaceBean.DestinationsBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        widthPixels = DeviceUtils.getDeviceScreenWidth(context);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, TargetPlaceBean.DestinationsBean item, int position) {
        holder.setImageByUrl(R.id.img_bg, item.getImage_url(), R.drawable.weit_place)
                .setText(R.id.txt_country_name, item.getName_zh_cn() + "\n" + item.getName_en())
                .setText(R.id.txt_poi_num, item.getPoi_count() + "旅行地");
        ImageView view = holder.getView(R.id.img_bg);
        view.setLayoutParams(new FrameLayout.LayoutParams(widthPixels / 2, (int) (widthPixels / 1.5)));
    }
}
