package com.example.dez.devlpart_remoteviewsdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyAppWidgetProvider extends AppWidgetProvider {
    public static final String TAG = "MyAppWidgetProvider";
    public static final String CLICK_ACTION = "com.ryg.chapter_5.action.CLICK";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG, "onReceive : action = " + intent.getAction());

        // 这里判断是自己的action，做自己的事情，比如小工具被点击了要干啥，这里是做一个动画效果
        if (intent.getAction().equals(CLICK_ACTION)) {
            Toast.makeText(context, "clicked it", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcBitmap = BitmapFactory.decodeResource(
                            context.getResources(), R.mipmap.icon1);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i++) {
                        float degree = (i * 10) % 360;
                        //没更新一次小部件重新打包一次remoteViews，
                        RemoteViews remoteViews = new RemoteViews(context
                                .getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.imageView1,
                                rotateBitmap(context, srcBitmap, degree));
                        Intent intentClick = new Intent();
                        intentClick.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent
                                .getBroadcast(context, 0, intentClick, 0);
                        remoteViews.setOnClickPendingIntent(R.id.imageView1, pendingIntent);
                        //更新37次小部件
                        appWidgetManager.updateAppWidget(new ComponentName(
                                context, MyAppWidgetProvider.class),remoteViews);
                        SystemClock.sleep(30);
                    }

                }
            }).start();
        }
    }

    /**
     * 每次窗口小部件被点击更新都调用一次该方法
     */
    //在widget的onUpdate方法里面创建了action绑定到widget按钮上，点击按钮发送广播，用AppWidgetManage对象
    // 的updateAppWidget（）方法更新。然后在onReceive（）方法根据逻辑我又new一个指向该widget的remoteView
    // 对象更新视图，又new一个AppWidgetManage对象更新
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate");

        final int counter = appWidgetIds.length;
        Log.i(TAG, "counter = " + counter);
        for (int i = 0; i < counter; i++) {
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdate(context, appWidgetManager, appWidgetId);
        }

    }

    /**
     * 窗口小部件更新
     *
     * @param context
     * @param appWidgetManger
     * @param appWidgetId
     */
    private void onWidgetUpdate(Context context,
                                AppWidgetManager appWidgetManger, int appWidgetId) {

        Log.i(TAG, "appWidgetId = " + appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget);

        // "窗口小部件"点击事件发送的Intent广播
        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.imageView1, pendingIntent);
        appWidgetManger.updateAppWidget(appWidgetId, remoteViews);
    }

    private Bitmap rotateBitmap(Context context, Bitmap srcBitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        //在源位图上裁剪出一个新位
        //图，并可以定义矩阵变换新位图。方法参数为一个源Bitmap对象,源图片中x方向的起始像素，源图片中y方向的起始像素，截图宽度（单位为像素），截图高度
        //（单位为像素），用于新图片变换的矩阵matrix，当矩阵的变换不只有平移时，如果此参数设置为真，进行滤波处理来改善图片的质量
        Bitmap tmpBitmap = Bitmap.createBitmap(srcBitmap, 0, 0,
                srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
        return tmpBitmap;
    }
}