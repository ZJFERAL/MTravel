package com.mcy.mtravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tipwiki.PagesBean;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.activity.TipsDetialActivity;
import com.zjf.core.adapter.CAbsViewAdapter;
import com.zjf.core.adapter.CAbsViewViewHolder;

import java.util.List;

/**
 * Created by jifengZhao on 2017/4/28.
 */

public class TipMenuItemAdapter extends CAbsViewAdapter<PagesBean> {

    private String mID;
    private int mGroupPosition;

    public TipMenuItemAdapter(Context context, List<PagesBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public int getGroupPosition() {
        return mGroupPosition;
    }

    public void setGroupPosition(int groupPosition) {
        mGroupPosition = groupPosition;
    }

    @Override
    protected void setItemView(final CAbsViewViewHolder holder, final PagesBean item, final int position) {
        holder.setText(R.id.txt_tip_menu_item, item.getTitle());
        holder.setOnclickListener(R.id.txt_tip_menu_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TipsDetialActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(FinalParams.TIPS_DETIAL_GROUP_POSITION, mGroupPosition);
                bundle.putInt(FinalParams.TIPS_DETIAL_CHILD_POSITION, position);
                bundle.putString(FinalParams.TIP_TITLE, item.getTitle());
                bundle.putString(FinalParams.TIP_ID, mID);
                intent.putExtra("data", bundle);
                mContext.startActivity(intent);
            }
        });
    }

}
