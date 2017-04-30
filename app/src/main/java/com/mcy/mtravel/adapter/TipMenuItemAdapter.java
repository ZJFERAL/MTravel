package com.mcy.mtravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.PagesBean;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.activity.TipsDetialActivity;
import com.zjf.core.adapter.CAbsViewAdapter;
import com.zjf.core.adapter.CAbsViewViewHolder;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/28.
 */

public class TipMenuItemAdapter extends CAbsViewAdapter<PagesBean> {

    private String mID;

    public TipMenuItemAdapter(Context context, List<PagesBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    @Override
    protected void setItemView(CAbsViewViewHolder holder, final PagesBean item) {
        holder.setText(R.id.txt_tip_menu_item, item.getTitle());
        holder.setOnclickListener(R.id.txt_tip_menu_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = item.getId();
                Intent intent = new Intent(mContext, TipsDetialActivity.class);
                intent.putExtra(FinalParams.TIPS_DETIAL_ID, itemId + "");
                intent.putExtra(FinalParams.TIPS_DETIAL_TYPE, FinalParams.TIPS_DETIAL_GROUP);
                intent.putExtra(FinalParams.TIP_TITLE, item.getTitle());
                intent.putExtra(FinalParams.TIP_ID, mID);
                mContext.startActivity(intent);
            }
        });
    }

}
