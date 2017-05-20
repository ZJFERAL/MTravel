package com.zjf.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by jifengZhao on 2017/4/28.
 */

public class MostLengthGridView extends GridView {


    public MostLengthGridView(Context context) {
        super(context);
    }

    public MostLengthGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, h);
    }
}
