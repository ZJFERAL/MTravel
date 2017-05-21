package com.mcy.mtravel.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.special.AttractionBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-05-20 下午 3:14
 */

public class SpecialLocaionAdapter extends CRecyclerViewAdapter<AttractionBean> {
    private int mWidth;

    public SpecialLocaionAdapter(Context context, List<AttractionBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(context);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, AttractionBean item, int position) {
        View imageView = holder.getView(R.id.img_cover);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(mWidth / 3, (int) (mWidth / 3.5)));
        holder.setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place)
                .setText(R.id.txt_name, item.getName());

        int resId = R.drawable.icon_location_5star;
        try {
            String score = item.getUser_score();
            int intScore = (int) Double.parseDouble(score);
            switch (intScore) {
                case 0:
                    resId = R.drawable.icon_location_0star;
                    break;
                case 1:
                    resId = R.drawable.icon_location_1star;
                    break;
                case 2:
                    resId = R.drawable.icon_location_2star;
                    break;
                case 3:
                    resId = R.drawable.icon_location_3star;
                    break;
                case 4:
                    resId = R.drawable.icon_location_4star;
                    break;
                case 5:
                    resId = R.drawable.icon_location_5star;
                    break;
            }
        } catch (Exception e) {
        } finally {
            holder.setImageResource(R.id.img_star, resId);
        }

    }
}
