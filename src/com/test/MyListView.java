package com.test;

import android.view.View.MeasureSpec;
import android.widget.ListView;

public class MyListView extends ListView {

    public MyListView(android.content.Context context,
                    android.util.AttributeSet attrs)
    {
            super(context, attrs);
    }

    /**
     * ���ò�����
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                            MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);

    }

}
