package com.mcy.mtravel.adapter;

import android.content.Context;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tipwiki.PagesBean;
import com.mcy.mtravel.entity.tipwiki.StrategyDetialBean;
import com.zjf.core.adapter.CAbsViewAdapter;
import com.zjf.core.adapter.CAbsViewViewHolder;
import com.zjf.core.widget.MostLengthGridView;

import java.util.List;

/**
 * Created by jifengZhao on 2017/4/28.
 */

public class TipMenuAdapter extends CAbsViewAdapter<StrategyDetialBean> {

    private String mID;

    public TipMenuAdapter(Context context, List<StrategyDetialBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
    }

    public void setID(String ID) {
        mID = ID;
    }

    @Override
    protected void setItemView(CAbsViewViewHolder holder, StrategyDetialBean item, int position) {
        int type = item.getCategory_type();
        String title;
        int resId;
        switch (type) {
            case 0:
                title = App.getStringRes(R.string.overview);
                resId = R.drawable.tips_overview;
                break;
            case 1:
                title = App.getStringRes(R.string.note);
                resId = R.drawable.tips_note;
                break;
            case 2:
                title = App.getStringRes(R.string.arrive);
                resId = R.drawable.tips_arrive;
                break;
            case 3:
                title = App.getStringRes(R.string.traffic);
                resId = R.drawable.tips_traffic;
                break;
            case 4:
                title = App.getStringRes(R.string.attraction);
                resId = R.drawable.tips_scenicspots;
                break;
            case 5:
                title = App.getStringRes(R.string.entertainment);
                resId = R.drawable.tips_entertainment;
                break;
            case 6:
                title = App.getStringRes(R.string.hotel);
                resId = R.drawable.tips_stay;
                break;
            case 7:
                title = App.getStringRes(R.string.food);
                resId = R.drawable.tips_food;
                break;
            case 8:
                title = App.getStringRes(R.string.shopping);
                resId = R.drawable.tips_shopping;
                break;
            case 9:
                title = App.getStringRes(R.string.departure);
                resId = R.drawable.tips_departure;
                break;
            case 10:
                title = App.getStringRes(R.string.author);
                resId = R.drawable.tips_author;
                break;
            case 11:
                title = App.getStringRes(R.string.other);
                resId = R.drawable.tips_more;
                break;
            default:
                title = App.getStringRes(R.string.app_name);
                resId = R.drawable.tips_entertainment;
                break;
        }
        holder.setText(R.id.txt_title, title).setImageResource(R.id.img_icon, resId);
        List<PagesBean> pages = item.getPages();
        TipMenuItemAdapter adapter = new TipMenuItemAdapter(mContext, pages, R.layout.item_tipmenu_item);
        adapter.setID(mID);
        adapter.setGroupPosition(position);
        MostLengthGridView view = holder.getView(R.id.item_view);
        view.setAdapter(adapter);
    }

}
