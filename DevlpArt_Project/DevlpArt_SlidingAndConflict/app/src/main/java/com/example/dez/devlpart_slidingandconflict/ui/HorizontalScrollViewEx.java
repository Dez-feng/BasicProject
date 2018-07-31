package com.example.dez.devlpart_slidingandconflict.ui;

/**
 * Created by Dez on 2018/7/16.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
//外部用HorizontalScrollViewEx模拟ViewPager
//HorizontalScrollViewEx的高度match_parent,宽度超过父容器,通过内容滑动切换View,实现HorizontalScrollView.
public class HorizontalScrollViewEx extends ViewGroup {
    private static final String TAG = "HorizontalScrollViewEx";

    private int mChildrenSize;
    private int mChildWidth;
    private int mChildIndex;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }

    //用外部拦截法解决滑动冲突
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {

            //拦截了ACTION_UP事件就拦截了后面的事件
            case MotionEvent.ACTION_DOWN: {
                intercepted = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    intercepted = true;//调用onTouchEvent
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //如果父容器需要则拦截
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {//如果横向移动则拦截
                    intercepted = true;//调用onTouchEvent
                } else {
                    intercepted = false;
                }
                break;
            }
            //ACTION_UP事件本身并没有太多意义，必须要返回false，因为如果返回true，就会导致子元素无法接收到ACTION_UP事件
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
            default:
                break;
        }

        Log.d(TAG, "intercepted=" + intercepted);
        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //用addMovement(MotionEvent)函数将Motion event加入到VelocityTracker类实例中.你可以
        // 使用getXVelocity() 或getXVelocity()获得横向和竖向的速率到速率时，但是使用它们之前
        // 请先调用computeCurrentVelocity(int)来初始化速率的单位
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (!mScroller.isFinished()) {   //如果滚动没有结束
                    mScroller.abortAnimation();
                }
                break;
            }
            //HorizontalScrollViewEx随手指左右滑动而滚动
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollBy(-deltaX, 0);
                break;
            }
            //松手后，根据速度和位移来判断上一页还是下一页
            case MotionEvent.ACTION_UP: {
                int scrollX = getScrollX();
                int scrollToChildIndex = scrollX / mChildWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50) {
                    //如果速度大于50则根据速度判断是上一页还是下一页
                    //速度为正，则向右滑，翻到上一页
                    //速度为负，则向左滑，翻到下一页
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                } else {
                    //如果速度小于50则根据位置判断是上一页还是下一页
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                //如果mChildIndex为-1或mChildrenSize则进行修正处理
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
                //水平滑动的距离
                //向右滑，dx为负，翻到上一页
                //向左滑，dx为正，翻到下一页
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx, 0);
                //重置mVelocityTracker
                mVelocityTracker.clear();
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

    //一般来说，自定义控件都会去重写View的onMeasure方法，因为该方法指定该控件在屏幕上的大小。
    //addView会触发onMeasure
    //onMeasure的widthMeasureSpec,heightMeasureSpec是int类型,一个int整数表示两个东西（大小模式和大小的值）
    //前面两位表示大小模式,后面30位表示大小的值
    //最高两位是00的时候表示"未指定模式"。即MeasureSpec.UNSPECIFIED
    //这种情况不多，一般都是父控件是AdapterView，通过measure方法传入的模式
    //最高两位是01的时候表示"'精确模式"。即MeasureSpec.EXACTLY
    //当我们将控件的layout_width或layout_height指定为具体数值时如android:layout_width="50dip"，或者为FILL_PARENT是，都是控件大小已经确定的情况
    //最高两位是11的时候表示"最大模式"。即MeasureSpec.AT_MOST
    //当控件的layout_width或layout_height指定为WRAP_CONTENT时，控件大小一般随着控件的子空间或内容进
    // 行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = 0;
        int measuredHeight = 0;
        final int childCount = getChildCount();
        //传入容器的widthMeasureSpec, heightMeasureSpec生成子view的ChildMeasureSpec
        //如下
        //int specSize = MeasureSpec.getSize(widthMeasureSpec);
        //int size = Math.max(0, specSize - padding);
        //else if (childDimension == LayoutParams.MATCH_PARENT) {
        // Child wants to be our size. So be it.
        //resultSize = size;
        //resultMode = MeasureSpec.EXACTLY;
        //return MeasureSpec.makeMeasureSpec(resultSize, resultMode)
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //MeasureSpec.getMode可以获取三种mode：UNSPECIFIED,EXACTLY,AT_MOST
        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);//设置视图实际的宽和高
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, childView.getMeasuredHeight());//设置视图实际的宽和高
        //根据相同子View的数目自动调整容器的宽度
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {//添加子View的时候执行这部分,因为子view的宽高是match——parent，所以自动填充多出的空间？！
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measuredWidth, heightSpaceSize);//设置视图实际的宽和高
        } else {
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);//设置视图实际的宽和高
        }
    }

    //addView会触发onLayout
    //覆盖改写了super.onLayout(changed, l, t, r, b);
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        mChildrenSize = childCount;

        //确定子View的位置
        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {  //View.setVisibility(View.GONE)使视图不可见并且不保留它在ViewGroup的位置，重新Layout
                final int childWidth = childView.getMeasuredWidth();
                mChildWidth = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth,
                        childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }

    private void smoothScrollBy(int dx, int dy) {  //dx为deltaX
        //设置相关参数，并使mScroller.computeScrollOffset()为非0
        mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
        //重绘View树，draw方法会调用computeScroll
        invalidate();
    }

    @Override
    //
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //重绘子View调用draw方法，反过来又调用computeScroll，直到mScroller.computeScrollOffset()为0
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        //回收mVelocityTracker
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}