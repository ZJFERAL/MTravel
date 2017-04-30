package com.mcy.mtravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mcy.mtravel.R;

import java.util.List;

/**
 * @author :ZJF
 * @version : 2017-04-20 下午 10:14
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private List<String> groups;
    private List<List<String>> items;
    private Context mContext;

    public ExpandableAdapter(List<String> groups, List<List<String>> items, Context context) {
        this.groups = groups;
        this.items = items;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return groups != null ? groups.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(groupPosition) != null ? items.get(groupPosition).size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expand_text, parent, false);
        }
        ((TextView) convertView).setText("DAY " + groups.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expand_text_item, parent, false);
        }
        String s = items.get(groupPosition).get(childPosition);
        ((TextView) convertView).setText("     · " + s);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
