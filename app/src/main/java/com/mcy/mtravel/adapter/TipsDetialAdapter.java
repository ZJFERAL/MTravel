package com.mcy.mtravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mcy.mtravel.R;
import com.mcy.mtravel.entity.tipwiki.ChildrenBean;
import com.mcy.mtravel.entity.tipwiki.Pages;
import com.mcy.mtravel.entity.tipwiki.PhotosBean;
import com.mcy.mtravel.entity.tipwiki.SectionsBean;
import com.mcy.mtravel.utils.FinalParams;
import com.mcy.mtravel.view.activity.TipsDetialActivity;
import com.zjf.core.adapter.CAbsViewAdapter;
import com.zjf.core.adapter.CAbsViewViewHolder;
import com.zjf.core.adapter.CRecyclerViewAdapter;
import com.zjf.core.adapter.CRecyclerViewViewHolder;
import com.zjf.core.utils.DeviceUtils;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-04-30 下午 9:13
 */

public class TipsDetialAdapter extends BaseExpandableListAdapter {

    private List<ChildrenBean> mChildrenBeanList;
    private Context mContext;
    private int width;

    public TipsDetialAdapter(List<ChildrenBean> childrenBeanList, Context context) {
        mChildrenBeanList = childrenBeanList;
        mContext = context;
        width = DeviceUtils.getDeviceScreenWidth(mContext);
    }

    @Override
    public int getGroupCount() {
        return mChildrenBeanList != null ? mChildrenBeanList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ChildrenBean childrenBean = mChildrenBeanList.get(groupPosition);
        return childrenBean.getSections() != null ? childrenBean.getSections().size() : 0;
    }

    @Override
    public ChildrenBean getGroup(int groupPosition) {
        return mChildrenBeanList.get(groupPosition);
    }

    @Override
    public SectionsBean getChild(int groupPosition, int childPosition) {
        return mChildrenBeanList.get(groupPosition).getSections().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mChildrenBeanList.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mChildrenBeanList.get(groupPosition).getSections().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tip_detial_expand_head, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        if (isExpanded) {
            holder.getImgArrow().setImageResource(R.drawable.ic_arrow_drop_up_yellow_700_24dp);
        } else {
            holder.getImgArrow().setImageResource(R.drawable.ic_arrow_drop_down_yellow_700_24dp);
        }
        holder.getTxtTitle().setText(mChildrenBeanList.get(groupPosition).getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tip_detial, parent, false);
            holder = new ChildHolder(convertView, mContext);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
            holder.txtTitle.setVisibility(View.GONE);
            holder.txtContent.setVisibility(View.GONE);
            holder.recyPhotos.setVisibility(View.GONE);
            holder.layBottom.setVisibility(View.GONE);
            holder.layPages.setVisibility(View.GONE);
            holder.txtEnd.setVisibility(View.GONE);

        }
        SectionsBean bean = mChildrenBeanList.get(groupPosition).getSections().get(childPosition);
        if (!TextUtils.isEmpty(bean.getTitle())) {
            holder.txtTitle.setVisibility(View.VISIBLE);
            holder.txtTitle.setText(bean.getTitle());
        }
        if (!TextUtils.isEmpty(bean.getDescription())) {
            holder.txtContent.setVisibility(View.VISIBLE);
            holder.txtContent.setText(bean.getDescription());
        }
        List<PhotosBean> photos = bean.getPhotos();
        if (photos != null && photos.size() != 0) {
            holder.recyPhotos.setVisibility(View.VISIBLE);
            holder.recyPhotos.setAdapter(new CRecyclerViewAdapter<PhotosBean>(mContext, photos, R.layout.item_photos) {
                @Override
                public void setConvertView(CRecyclerViewViewHolder holder, PhotosBean item, int position) {
                    int realheight = (int) (width / 2.2);
                    float scale = realheight * 1f / item.getImage_height();
                    int realWidth = (int) (item.getImage_width() * scale);
                    ImageView view = holder.getView(R.id.img_photo);
                    view.setLayoutParams(new FrameLayout.LayoutParams(realWidth, realheight));
                    holder.setImageByUrl(R.id.img_photo, item.getImage_url(), R.drawable.weit_place);
                }
            });
        }
        List<Pages> pages = bean.getPages();
        if (pages != null && pages.size() != 0) {
            holder.layPages.setVisibility(View.VISIBLE);
            holder.listPages.setAdapter(new CAbsViewAdapter<Pages>(mContext, pages, R.layout.item_tip_detial_pages) {
                @Override
                protected void setItemView(CAbsViewViewHolder holder, final Pages item, int position) {
                    holder.setText(R.id.txt_pages, item.getTitle())
                            .setOnclickListener(R.id.txt_pages, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = item.getDestination_id();
                                    Intent intent = new Intent(mContext, TipsDetialActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString(FinalParams.TIP_TITLE, item.getTitle());
                                    bundle.putString(FinalParams.TIP_ID, id + "");
                                    bundle.putString(FinalParams.TIPS_DETIAL_CHILD_TITLE, item.getTitle());
                                    intent.putExtra("data", bundle);
                                    mContext.startActivity(intent);
                                }
                            });
                }
            });

        }
        if (!TextUtils.isEmpty(bean.getTravel_date())) {
            holder.layBottom.setVisibility(View.VISIBLE);
            holder.txtTime.setText(bean.getTravel_date());
        }
        if (bean.getUser() != null && (!TextUtils.isEmpty(bean.getUser().getName()))) {
            holder.layBottom.setVisibility(View.VISIBLE);
            holder.txtAuthor.setText(bean.getUser().getName());
        }

        if (groupPosition == getGroupCount() - 1 && childPosition == getChildrenCount(groupPosition) - 1) {
            holder.txtEnd.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class GroupHolder {
        private TextView txtTitle;
        private ImageView imgArrow;

        public GroupHolder(View convertView) {
            txtTitle = (TextView) convertView.findViewById(R.id.txt_head_title);
            imgArrow = (ImageView) convertView.findViewById(R.id.img_arrow);
        }

        public TextView getTxtTitle() {
            return txtTitle;
        }

        public ImageView getImgArrow() {
            return imgArrow;
        }
    }

    static class ChildHolder {
        private int width;
        TextView txtTitle, txtTime, txtAuthor, txtContent, txtEnd;
        RecyclerView recyPhotos;
        LinearLayout layBottom, layPages;
        ListView listPages;

        public ChildHolder(View convertView, Context context) {
            width = DeviceUtils.getDeviceScreenWidth(context);
            txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
            txtContent = (TextView) convertView.findViewById(R.id.txt_content);
            txtTime = (TextView) convertView.findViewById(R.id.txt_time);
            txtAuthor = (TextView) convertView.findViewById(R.id.txt_author);
            recyPhotos = (RecyclerView) convertView.findViewById(R.id.item_recy);
            layBottom = (LinearLayout) convertView.findViewById(R.id.lay_bottom);
            layPages = (LinearLayout) convertView.findViewById(R.id.layout_pages);
            listPages = (ListView) convertView.findViewById(R.id.list_pages);
            txtEnd = (TextView) convertView.findViewById(R.id.txt_end);


            recyPhotos.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, (int) (width / 2.2)));
            recyPhotos.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
    }
}
