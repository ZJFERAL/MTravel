package com.mcy.mtravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcy.mtravel.App;
import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.travel.AttractionContentsBean;
import com.mcy.mtravel.entity.travel.AttractionsBean;
import com.mcy.mtravel.entity.travel.HotelsBean;
import com.mcy.mtravel.entity.travel.TravelNotesBean;
import com.mcy.mtravel.entity.travel.TravelTripBean;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.activity.TravelDetialActivity;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

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


    @Override
    public void setConvertView(CRecyclerViewViewHolder holder, AttractionContentsBean item, int position) {
        //标题
        TextView titleView = holder.getView(R.id.txt_day_num);
        titleView.setVisibility(View.GONE);
        String title = item.getTitle();
        String lastTitle = " ";
        if (position != 0) {
            lastTitle = mData.get(position - 1).getTitle();
        }
        if (!title.equals(lastTitle)) {
            titleView.setVisibility(View.VISIBLE);
            titleView.setText(title);
        }

        //内容
        TextView txtDesView = holder.getView(R.id.txt_desc);
        String description = item.getDescription();
        if (TextUtils.isEmpty(description)) {
            txtDesView.setVisibility(View.GONE);
        } else {
            txtDesView.setVisibility(View.VISIBLE);
            txtDesView.setText(Html.fromHtml(description));
        }

        //图片recycler
        RecyclerView recyImageView = holder.getView(R.id.item_recy);
        recyImageView.setVisibility(View.GONE);
        List<TravelNotesBean> notes = item.getNotes();
        if (notes != null && notes.size() > 0) {
            recyImageView.setVisibility(View.VISIBLE);
            recyImageView.setLayoutParams(new LinearLayout.LayoutParams(mWidth, (int) (mWidth / 2.2)));
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
                    photo.setLayoutParams(new RelativeLayout.LayoutParams(realWidth, realheight));
                    holder.setImageByUrl(R.id.img_photo, item.getPhoto_url(), R.drawable.weit_place);
                }
            });
        }
        //作者 时间
        TravelTripBean trip = item.getTrip();
        if (trip != null) {
            holder.getView(R.id.layout_author).setVisibility(View.VISIBLE);
            holder.setText(R.id.txt_author, trip.getUser().getName())
                    .setText(R.id.txt_time, trip.getStart_date());
        } else {
            holder.getView(R.id.layout_author).setVisibility(View.GONE);
        }


        //底部 旅行地

        View placeLayout = holder.getView(R.id.lay_bottom_travel_place);
        View hotelLayout = holder.getView(R.id.lay_bottom_hotel);
        if (position == getRealCount() - 1) {
            if (mAttractionsBeans != null) {
                placeLayout.setVisibility(View.VISIBLE);
                RecyclerView locationRecy = holder.getView(R.id.item_recy_location);
                holder.setText(R.id.txt_about_location, App.getStringRes(R.string.around_loaction) + " " + mAttractionsBeans.size());
                locationRecy.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.HORIZONTAL, false));
                locationRecy.setAdapter(
                        new CRecyclerViewAdapter<AttractionsBean>
                                (mContext, mAttractionsBeans,
                                        R.layout.item_travel_bottom) {
                            @Override
                            public void setConvertView(CRecyclerViewViewHolder holder, final AttractionsBean item, final int position) {
                                View holderView = holder.getView(R.id.layout_root);
                                holderView.setLayoutParams(new FrameLayout.LayoutParams(mWidth / 3, FrameLayout.LayoutParams.WRAP_CONTENT));
                                View imageView = holder.getView(R.id.img_cover);
                                imageView.setLayoutParams(new RelativeLayout.LayoutParams(mWidth / 3, (int) (mWidth / 3.5)));
                                holder.setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place)
                                        .setText(R.id.txt_name, item.getName_zh_cn())
                                        .setText(R.id.txt_distance, ((int) (item.getDistance() * 1000)) + " m")
                                        .setOnclickListener(R.id.img_cover, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                int id = item.getId();
                                                Intent intent = new Intent(mContext, TravelDetialActivity.class);
                                                intent.putExtra(FinalParams.TRIPS_DETIAL_ID, id + "");
                                                mContext.startActivity(intent);
                                            }
                                        });
                            }
                        });
            }
            if (mHotelsBeens != null) {
                hotelLayout.setVisibility(View.VISIBLE);
                RecyclerView hotelRecy = holder.getView(R.id.item_recy_hotel);
                holder.setText(R.id.txt_about_hotel, App.getStringRes(R.string.around_hotel) + " " + mHotelsBeens.size());
                hotelRecy.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.HORIZONTAL, false));
                hotelRecy.setAdapter(
                        new CRecyclerViewAdapter<HotelsBean>
                                (mContext, mHotelsBeens,
                                        R.layout.item_travel_bottom) {
                            @Override
                            public void setConvertView(CRecyclerViewViewHolder holder, HotelsBean item, int position) {
                                View holderView = holder.getView(R.id.layout_root);
                                holderView.setLayoutParams(new FrameLayout.LayoutParams(mWidth / 3, FrameLayout.LayoutParams.WRAP_CONTENT));
                                View imageView = holder.getView(R.id.img_cover);
                                imageView.setLayoutParams(new RelativeLayout.LayoutParams(mWidth / 3, (int) (mWidth / 3.5)));
                                holder.setImageByUrl(R.id.img_cover, item.getImage_url(), R.drawable.weit_place)
                                        .setText(R.id.txt_name, item.getName_zh_cn())
                                        .setText(R.id.txt_distance, ((int) (item.getDistance() * 1000)) + " m");
                            }
                        });
            }
        } else {
            placeLayout.setVisibility(View.GONE);
            hotelLayout.setVisibility(View.GONE);
        }
    }
}
