package com.mcy.mtravel.adapter;

import android.content.Context;
import android.view.View;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.PagesBean;
import com.zjf.core.adapter.CAbsViewAdapter;
import com.zjf.core.adapter.CAbsViewViewHolder;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/28.
 */

public class TipMenuItemAdapter extends CAbsViewAdapter<PagesBean> {


    public TipMenuItemAdapter(Context context, List<PagesBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    @Override
    protected void setItemView(CAbsViewViewHolder holder, final PagesBean item) {
        holder.setText(R.id.txt_tip_menu_item, item.getTitle());
        holder.setOnclickListener(R.id.txt_tip_menu_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = item.getId();
                
            }
        });
    }

}
