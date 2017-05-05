package com.mcy.mtravel.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tip.TravelListBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public class TravelListAdapter extends CRecyclerViewAdapter<TravelListBean> {

    private int mWidth;

    public TravelListAdapter(Context context, List<TravelListBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(mContext);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, TravelListBean item, int position) {
        ImageView view = holder.getView(R.id.img_cover);
        view.setLayoutParams(new RelativeLayout.LayoutParams((int) (mWidth * 2f / 3), (int) (mWidth / 3.6)));
        holder.setText(R.id.txt_num, item.getAttraction_trips_count() + " 篇游记")
                .setText(R.id.txt_name, item.getName() + "\n" + item.getName_en())
                .setText(R.id.txt_content, item.getDescription_summary())
                .setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place);
        int resId = R.drawable.icon_location_0star;
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
