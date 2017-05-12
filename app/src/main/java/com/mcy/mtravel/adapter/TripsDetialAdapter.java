package com.mcy.mtravel.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tiptrips.PlanNodesBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;
import com.zjf.core.utils.LogUtils;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-05-06 下午 3:02
 */

public class TripsDetialAdapter extends CRecyclerViewAdapter<PlanNodesBean> {

    private int mWidth;

    public TripsDetialAdapter(Context context, List<PlanNodesBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(mContext);
    }

    @Override
    public int getItemType(int position) {
        return mData.get(position).isCandidate() ? 1 : 0;
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, final PlanNodesBean item, int position) {
        if (getItemType(position) == 0) {
            TextView view = holder.getView(R.id.txt_day_num);
            view.setVisibility(View.GONE);
            String title = item.getFirstTitle();
            String lastTitle = mData.get(position == 0 ? 0 : position - 1).getFirstTitle();
            if (position == 0 || ((!TextUtils.isEmpty(title)) && (!title.equals(lastTitle)))) {
                view.setVisibility(View.VISIBLE);
                view.setText(title);
            }


            ImageView typeView = holder.getView(R.id.img_type);
            typeView.setImageResource(R.drawable.scenery_travel);
            View jumpview = holder.getView(R.id.img_jump);
            jumpview.setVisibility(View.VISIBLE);
            if (item.getPosition() == -1) {
                jumpview.setVisibility(View.GONE);
                typeView.setImageResource(R.drawable.note_travel);
            }
            String type = item.getEntry_type();
            LogUtils.e("setConvertView:type", type);
        }
        holder.setText(R.id.txt_place_name, item.getEntry_name())
                .setText(R.id.txt_content, item.getTips()).setOnclickListener(R.id.layout_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = item.getEntry_id();
                if (id != 0) {

                }
                LogUtils.e("Entry_id:", id + "");
            }
        });
        String url = item.getImage_url();
        if (!TextUtils.isEmpty(url)) {
            ImageView view = holder.getView(R.id.img_cover);
            view.setLayoutParams(new LinearLayout.LayoutParams(mWidth, (int) (mWidth / 2.1)));
            holder.setImageByUrl(R.id.img_cover, url, R.drawable.weit_place);
        }
    }
}
