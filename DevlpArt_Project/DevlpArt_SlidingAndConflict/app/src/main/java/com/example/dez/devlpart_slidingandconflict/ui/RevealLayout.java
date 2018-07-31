package com.example.dez.devlpart_slidingandconflict.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dez.devlpart_slidingandconflict.R;

import java.util.ArrayList;

/**
 * Created by Dez on 2018/5/24.
 */

/**
 * 一个特殊的LinearLayout,任何放入内部的clickable元素都具有波纹效果，当它被点击的时候，
 * 为了性能，尽量不要在内部放入复杂的元素
 * note: long click listener is not supported current for fix compatible bug.
 */

public class RevealLayout extends LinearLayout{

    private static final String TAG = "DxRevealLayout";
    private static final boolean DEBUG = true;

    private int mTargetWidth;
    private int mTargetHeight;
    private int mMinBetweenWidthAndHeight;
    private int mMaxBetweenWidthAndHeight;
    private int mMaxRevealRadius;
    private int mRevealRadius = 0;
    private int mRevealRadiusGap;
    private float mCenterX;
    private float mCenterY;

    private boolean mShouldDoAnimation = false;
    private boolean mIsPressed = false;
    private int  INVALIDATE_DURATION = 40;

    private int[] mLocationInScreen = new int[2];
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private View mTouchTarget;
    private DispatchUpTouchEventRunnable mDispatchUpTouchEventRunnable = new DispatchUpTouchEventRunnable();

    public RevealLayout(Context context) {
        super(context);
        init();
    }

    public RevealLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public RevealLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        setWillNotDraw(false);
        mPaint.setColor(getResources().getColor(R.color.reveal_color));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.getLocationOnScreen(mLocationInScreen);
    }

    //为dispatchdraw初始化参数
    private void initParametersForChild(MotionEvent event,View view){

        mCenterX = event.getX();
        mCenterY = event.getY();
        mTargetWidth = view.getMeasuredWidth();
        mTargetHeight = view.getMeasuredHeight();
        mMinBetweenWidthAndHeight = Math.min(mTargetWidth,mTargetHeight);
        mMaxBetweenWidthAndHeight = Math.max(mTargetWidth,mTargetHeight);
        mRevealRadius = 0;
        mRevealRadiusGap = mMinBetweenWidthAndHeight / 8;
        mShouldDoAnimation = true;
        mIsPressed = true;

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        //int left = location[0] - mLocationInScreen[0];
        //int transformedCenterX = (int)mCenterX - left;
        mMaxRevealRadius = Math.max((int)mCenterX,mTargetWidth - (int)mCenterX);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!mShouldDoAnimation || mTargetWidth <= 0 || mTouchTarget == null) {
            return;
        }

        //半径递增算法
        if (mRevealRadius > mMinBetweenWidthAndHeight / 2) {
            //四倍速率
            mRevealRadius += mRevealRadiusGap * 4;
        } else {
            mRevealRadius += mRevealRadiusGap;
        }
        this.getLocationOnScreen(mLocationInScreen);
        int[] location = new int[2];
        mTouchTarget.getLocationOnScreen(location);
        int left = location[0] - mLocationInScreen[0];
        int top = location[1] - mLocationInScreen[1];
        int right = left + mTouchTarget.getMeasuredWidth();
        int bottom = top + mTouchTarget.getMeasuredHeight();

        //画一个半径越来越大的圆
        //canvas.save();
        canvas.clipRect(left, top, right, bottom);
        canvas.drawCircle(mCenterX, mCenterY, mRevealRadius, mPaint);
        //canvas.restore();

        if (mRevealRadius <= mMaxRevealRadius) {
            //不断调用自身更新画圆
            postInvalidateDelayed(INVALIDATE_DURATION, left, top, right, bottom);
        } else if (!mIsPressed) {
            mShouldDoAnimation = false;
            //恢复原状
            postInvalidateDelayed(INVALIDATE_DURATION, left, top, right, bottom);
        }
    }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {

            int x = (int)ev.getRawX();
            int y = (int)ev.getRawY();
            int action = ev.getAction();

            if(action == MotionEvent.ACTION_DOWN)
            {
                View touchTarget = getTouchTarget(this,x,y);
                if(touchTarget != null && touchTarget.isClickable() && touchTarget.isEnabled())
                {
                     mTouchTarget = touchTarget;
                     initParametersForChild(ev,touchTarget);
                     postInvalidateDelayed(INVALIDATE_DURATION);
                }

            }
            else if(action == MotionEvent.ACTION_UP)
            {
                mIsPressed = false;
                postInvalidateDelayed(INVALIDATE_DURATION);
                mDispatchUpTouchEventRunnable.event = ev;
                postDelayed(mDispatchUpTouchEventRunnable,200);
                return true;
            }
            else if(action == MotionEvent.ACTION_CANCEL)
            {
                mIsPressed = false;
                postInvalidateDelayed(INVALIDATE_DURATION);
            }

            return super.dispatchTouchEvent(ev);
        }

    private View getTouchTarget(View view, int x, int y){

        View target = null;
        //得到所有可点击的view
        ArrayList<View> TouchableViews = view.getTouchables();
        for(View v:TouchableViews)
        {
            if(IsTouchPointInView(v,x,y)) {
                target = v;
                break;
            }
        }
        return target;
    }

    private boolean IsTouchPointInView(View view,int x,int y)
    {
        int[] Location = new int[2];
        view.getLocationOnScreen(Location);

        int left = Location[0];
        int top = Location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();

        if(view.isClickable() && x >= left && x <= right && y >= top && y <= bottom)
            return true;

        return false;
    }


    //@Override
    //public boolean performClick() {
    //    postDelayed(this,400);
    //    return true;
    //}

    //@Override
    //public void run() {
    //    super.performClick();
    //}

    private class DispatchUpTouchEventRunnable implements Runnable{

        public MotionEvent event;
        @Override
        public void run() {

            if(mTouchTarget == null || !mTouchTarget.isEnabled())
                return;

            if(IsTouchPointInView(mTouchTarget,(int)event.getRawX(),(int)event.getRawY()))
                mTouchTarget.dispatchTouchEvent(event);

        }
    };



}


