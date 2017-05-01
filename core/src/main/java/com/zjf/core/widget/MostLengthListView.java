package com.zjf.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author :ZJF
 * @version : 2017-05-01 下午 8:48
 */

public class MostLengthListView extends ListView {
    public MostLengthListView(Context context) {
        super(context);
    }

    public MostLengthListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, h);
    }
}
