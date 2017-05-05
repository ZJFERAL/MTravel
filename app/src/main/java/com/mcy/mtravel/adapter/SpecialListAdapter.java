package com.mcy.mtravel.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tip.SpecialListBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/5/3.
 */

public class SpecialListAdapter extends CRecyclerViewAdapter<SpecialListBean> {

    private int mWidth;

    public SpecialListAdapter(Context context, List<SpecialListBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(mContext);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, SpecialListBean item, int position) {
        holder.setText(R.id.txt_title, item.getName())
                .setText(R.id.txt_content, item.getTitle())
                .setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place);
        ImageView view = holder.getView(R.id.img_cover);
        view.setLayoutParams(new RelativeLayout.LayoutParams(mWidth, (int) (mWidth / 1.5)));
    }
}
