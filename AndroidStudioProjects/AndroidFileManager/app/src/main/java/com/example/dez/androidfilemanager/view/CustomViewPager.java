package com.example.dez.androidfilemanager.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Dez on 2017/7/21.
 */

public class CustomViewPager extends ViewPager {

    private boolean mIsPagingEnabled = false;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.mIsPagingEnabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mIsPagingEnabled && super.onInterceptTouchEvent(ev);
    }

    public void setIsPagingEnabled(boolean active){
        mIsPagingEnabled = active;
    }

}






