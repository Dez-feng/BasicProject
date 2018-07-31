package com.example.dez.devlpart_slidingandconflict.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Dez on 2018/7/28.
 */

public class ListViewEx extends ListView {

    private static final String TAG = "ListViewEx";

    private HorizontalScrollViewEx2 mHorizontalScrollViewEx;

    private int mLastX = 0;
    private int mLastY = 0;
    public ListViewEx(Context context) {
        super(context);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHorizontalScrollViewEx2(HorizontalScrollViewEx2  horizontalScrollViewEx2)
    {
        mHorizontalScrollViewEx = horizontalScrollViewEx2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int x = (int)ev.getX();
        int y = (int)ev.getY();

        switch(ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mHorizontalScrollViewEx.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "dx:" + deltaX + " dy:" + deltaY);
                if(Math.abs(deltaX) > Math.abs(deltaY))
                    mHorizontalScrollViewEx.requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }
}



