package com.mcy.mtravel.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.travel.AttractionContentsBean;
import com.mcy.mtravel.entity.travel.AttractionsBean;
import com.mcy.mtravel.entity.travel.HotelsBean;
import com.mcy.mtravel.entity.travel.TravelNotesBean;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

import static android.R.attr.width;

/**
 * @author :ZJF
 * @version : 2017-05-21 上午 10:48
 */

public class TravelDetialAdapter extends CRecyclerViewAdapter<AttractionContentsBean> {

    private List<HotelsBean> mHotelsBeens;
    private List<AttractionsBean> mAttractionsBeans;
    private int mWidth;

    public TravelDetialAdapter(Context context, List<AttractionContentsBean> data, int... itemLayoutIds) {
        super(context, data, itemLayoutIds);
        mWidth = DeviceUtils.getDeviceScreenWidth(context);
    }

    public void setHotelsBeens(List<HotelsBean> hotelsBeens) {
        mHotelsBeens = hotelsBeens;
    }

    public void setAttractionsBeans(List<AttractionsBean> attractionsBeans) {
        mAttractionsBeans = attractionsBeans;
    }

    private String lastTitle = " ";

    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, AttractionContentsBean item, int position) {
        //标题
        TextView titleView = holder.getView(R.id.txt_day_num);
        titleView.setVisibility(View.GONE);
        String title = item.getTitle();
        if (!title.equals(lastTitle)) {
            titleView.setVisibility(View.VISIBLE);
            titleView.setText(title);
        }

        //内容
        WebView webDesView = holder.getView(R.id.web_desc);
        webDesView.setVisibility(View.GONE);
        TextView txtDesView = holder.getView(R.id.txt_desc);
        txtDesView.setVisibility(View.GONE);
        if (position == 0) {
            webDesView.setVisibility(View.VISIBLE);
            webDesView.loadUrl(item.getDescription());
        } else {
            txtDesView.setVisibility(View.VISIBLE);
            txtDesView.setText(item.getDescription());
        }
        //图片recycler
        RecyclerView recyImageView = holder.getView(R.id.item_recy);
        recyImageView.setVisibility(View.GONE);
        List<TravelNotesBean> notes = item.getNotes();
        if (notes != null && notes.size() > 0) {
            recyImageView.setVisibility(View.VISIBLE);
            recyImageView.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, (int) (width / 2.2)));
            recyImageView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            recyImageView.setAdapter(new CRecyclerViewAdapter<TravelNotesBean>(mContext, notes, R.layout.item_photos_with_bottomline) {
                @Override
                public void setConvertView(CRecyclerViewViewHolder holder, TravelNotesBean item, int position) {
                    String description = item.getDescription();
                    TextView view = holder.getView(R.id.txt_bottom_photo_name);
                    if (TextUtils.isEmpty(description)) {
                        view.setVisibility(View.GONE);
                    } else {
                        view.setVisibility(View.VISIBLE);
                        view.setText(description);
                    }
                    ImageView photo = holder.getView(R.id.img_photo);
                    int realheight = (int) (mWidth / 2.2);
                    float scale = realheight * 1f / item.getHeight();
                    int realWidth = (int) (item.getWidth() * scale);
                    photo.setLayoutParams(new FrameLayout.LayoutParams(realWidth, realheight));
                    holder.setImageByUrl(R.id.img_photo, item.getPhoto_url(), R.drawable.weit_place);
                }
            });
        }

        //底部 旅行地
        RecyclerView locationRecy = holder.getView(R.id.item_recy_location);
        RecyclerView hotelRecy = holder.getView(R.id.item_recy_hotel);
        locationRecy.setVisibility(View.GONE);
        hotelRecy.setVisibility(View.GONE);
        if (position == getRealCount() - 1) {
            if (mAttractionsBeans != null) {
                locationRecy.setVisibility(View.VISIBLE);
                locationRecy.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.HORIZONTAL, false));
                locationRecy.setAdapter(
                        new CRecyclerViewAdapter<AttractionsBean>
                                (mContext, mAttractionsBeans,
                                        R.layout.item_travel_bottom) {
                            @Override
                            public void setConvertView(CRecyclerViewViewHolder holder, AttractionsBean item, int position) {
                                View imageView = holder.getView(R.id.img_cover);
                                imageView.setLayoutParams(new LinearLayout.LayoutParams(mWidth / 4, mWidth / 5));
                                holder.setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place)
                                        .setText(R.id.txt_name, item.getName_zh_cn())
                                        .setText(R.id.txt_distance, ((int) (item.getDistance() * 1000)) + " m");


                            }
                        });
            }
            if (mHotelsBeens != null) {
                hotelRecy.setVisibility(View.VISIBLE);
                locationRecy.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.HORIZONTAL, false));
                locationRecy.setAdapter(
                        new CRecyclerViewAdapter<HotelsBean>
                                (mContext, mHotelsBeens,
                                        R.layout.item_travel_bottom) {
                            @Override
                            public void setConvertView(CRecyclerViewViewHolder holder, HotelsBean item, int position) {
                                View imageView = holder.getView(R.id.img_cover);
                                imageView.setLayoutParams(new LinearLayout.LayoutParams(mWidth / 4, mWidth / 5));
                                holder.setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place)
                                        .setText(R.id.txt_name, item.getName_zh_cn())
                                        .setText(R.id.txt_distance, ((int) (item.getDistance() * 1000)) + " m");
                            }
                        });
            }
        }
    }
}
