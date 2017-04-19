package com.mcy.mtravel.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.TargetPlaceBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;

import java.util.List;

/**
 * Created by zhaojifeng on 2017/4/19.
 */

public class PlanZoneAdapter extends CRecyclerViewAdapter<TargetPlaceBean> {

    public PlanZoneAdapter(Context context, List<TargetPlaceBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, TargetPlaceBean item, int position) {
        holder.setText(R.id.txt_zone_title, item.getCategory());
        RecyclerView view = holder.getView(R.id.recy_zone_item);
        view.setLayoutManager(new GridLayoutManager(mContext, 2));
        view.setAdapter(new PlanCountryAdapter(mContext, item.getDestinations(), R.layout.item_plan_country));
        view.addOnItemTouchListener(new CRecyclerViewAdapter.RecyclerItemClickListener(mContext, new CRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        }));
    }
}