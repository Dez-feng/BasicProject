package com.example.dez.devlpart_slidingandconflict.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Dez on 2018/5/18.
 */

public class TestButton extends AppCompatTextView{

    private static final String TAG = "TextButton";

    private int mScaledTouchSlop;
    private int mLastX = 0;
    private int mLastY = 0;

    public TestButton(Context context) {
        this(context,null);
    }

    public TestButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.d(TAG,"sts:" + mScaledTouchSlop);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int)event.getRawX();
        int y = (int)event.getRawY();
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:{
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
                int translationX = (int)ViewHelper.getTranslationX(this) + deltaX;
                int translationY = (int)ViewHelper.getTranslationY(this) + deltaY;
                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this,translationY);
                break;
            }
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;

        }

        mLastX = x;
        mLastY = y;
        return true;
    }
}
