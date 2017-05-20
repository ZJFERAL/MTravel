package com.mcy.mtravel.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.index.IndexBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.TimeUtils;

import java.util.List;

/**
 * Created by jifengZhao on 2017/4/17.
 */

public class NewsAdapter extends CRecyclerViewAdapter<IndexBean.DataBean.FeedsBean.ListBean> {

    private int widthPixels;

    public NewsAdapter(Context context, List<IndexBean.DataBean.FeedsBean.ListBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, IndexBean.DataBean.FeedsBean.ListBean item, int position) {

        holder.setImageByUrl(R.id.img_cover, item.getPage_cover(),
                R.drawable.weit_place)
                .setText(R.id.txt_title, item.getTitle().replace("穷游锦囊", "漫途"))
                .setText(R.id.txt_subject, item.getPage_desc());
        if (item.getTags() != null && item.getTags().size() > 0) {
            holder.setText(R.id.txt_time_tag, "#" + item.getTags().get(0));
        }
        if (item.getAuthors() != null && item.getAuthors().size() > 0) {
            holder.setText(R.id.txt_time_author,
                    TimeUtils.millis2String(item.getCtime(), "yyyy-MM-dd")
                            + "  ·  " + item.getAuthors().get(0).getName().replace("锦囊", "漫途"));
        }
        ImageView imageView = holder.getView(R.id.img_cover);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(widthPixels, widthPixels / 3));

    }
}
