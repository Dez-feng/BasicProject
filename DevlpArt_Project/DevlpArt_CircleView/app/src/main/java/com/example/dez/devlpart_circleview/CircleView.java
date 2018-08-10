package com.example.dez.devlpart_circleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Dez on 2018/8/8.
 */

public class CircleView extends View{

    private final String Tag = "CircleView";
    private int mColor = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        //不设this，CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)不执行
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color,Color.RED);
        a.recycle();
        init();

    }

    private void init()
    {
        setWillNotDraw(false);
        //mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST)
        {
            setMeasuredDimension(200,200);
        }
        else if(widthSpecMode == MeasureSpec.AT_MOST)
        {
            setMeasuredDimension(200,heightSpecSize);
        }
        else if(heightSpecMode == MeasureSpec.AT_MOST)
        {
            setMeasuredDimension(widthSpecSize,200);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(Tag,"onDraw");
        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width,height)/2;

        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColor);
        canvas.drawCircle(paddingLeft + width/2,paddingTop + height/2,radius,mPaint);

    }
}
