package com.example.dez.devlpart_windowdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

public class TestActivity extends Activity implements OnTouchListener {

    private static final String TAG = "TestActivity";

    private Button mCreateWindowButton;

    private Button mFloatingButton;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;

    private int mLastX;
    private int mLastY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mCreateWindowButton = (Button) findViewById(R.id.button1);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    public void onButtonClick(View v) {
        if (v == mCreateWindowButton) {
            mFloatingButton = new Button(this);
            mFloatingButton.setText("click me");
            mLayoutParams = new WindowManager.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0, 0,
                    PixelFormat.TRANSPARENT);  //设置PixelFormat.TRANSPARENT使窗口支持透明化,然后可以通过setAlpha，drawColor等函数来设置窗口透明度
            mLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL    //将当前Window区域以外的单击事件传递给底层的Window
                    | LayoutParams.FLAG_NOT_FOCUSABLE    //表示Window不需要获取焦点，也不需要接收各种输入事件
                    | LayoutParams.FLAG_SHOW_WHEN_LOCKED;    //让Window显示在锁屏的界面上
            mLayoutParams.type = LayoutParams.TYPE_SYSTEM_ERROR;    //指定Window类型为系统Window
            mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;      //设置坐标的基准点
            mLayoutParams.x = 100;
            mLayoutParams.y = 300;
            mFloatingButton.setOnTouchListener(this);
            mWindowManager.addView(mFloatingButton, mLayoutParams);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //引用全局的mLayoutParams
                //mLayoutParams.x = mLayoutParams.x + rawX - mLastX;
                //mLayoutParams.y = mLayoutParams.y + rawY - mLastY;
                //mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                LayoutParams mLayoutParams = (android.view.WindowManager.LayoutParams)v.getLayoutParams();
                mLayoutParams.x = (int)(((LayoutParams)v.getLayoutParams()).x) + rawX - mLastX;
                mLayoutParams.y = (int)(((LayoutParams)v.getLayoutParams()).y) + rawY - mLastY;
                mWindowManager.updateViewLayout(v,mLayoutParams);
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        mLastX = (int)event.getRawX();
        mLastY = (int)event.getRawY();
        return false;
    }

    @Override
    protected void onDestroy() {
        try {
            mWindowManager.removeView(mFloatingButton);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
