package com.mcy.mtravel.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.StrategyBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-04-26 下午 9:01
 */

public class StrategyAdapter extends CRecyclerViewAdapter<StrategyBean> {

    private int mWidth;

    public StrategyAdapter(Context context, List<StrategyBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(context);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, StrategyBean item, int position) {
        holder.setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place)
                .setText(R.id.txt_place_name, item.getName_zh_cn())
                .setOnclickListener(R.id.tips, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setOnclickListener(R.id.trip, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).setOnclickListener(R.id.travel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).setOnclickListener(R.id.special, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageView view = holder.getView(R.id.img_cover);
        view.setLayoutParams(new LinearLayout.LayoutParams(mWidth, (int) (mWidth / 1.7)));
    }
}
