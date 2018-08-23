package com.example.dez.devlpart_remoteviewsdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

public class TestActivity extends Activity implements OnClickListener {

    private static final String TAG = "TestActivity";

    private Button mButton1;
    private View mButton2;

    private static int sId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (TextView) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View v) {
        if (v == mButton1) {
            sId ++;
            Notification notification = new Notification();
            //notification.icon = R.mipmap.ic_launcher;
            //notification.tickerText = "hello world";
            //notification.when = System.currentTimeMillis();
            //notification.flags = Notification.FLAG_AUTO_CANCEL;
            Intent intent = new Intent(this, DemoActivity_2.class);
            //PendingIntent.getActivity跳转到Activity
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Builder builder = new Notification.Builder(this)
                    .setAutoCancel(true)    // 将AutoCancel设为true后，当你点击通知栏的notification后，它会自动被取消消失
                    .setContentTitle("title")
                    .setContentText("hello world")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setOngoing(true);  // 将Ongoing设为true 那么notification将不能滑动删除
            notification=builder.getNotification();
            //notification.setLatestEventInfo(this, "chapter_5", "this is notification.", pendingIntent);
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            //sId是应用全局唯一安全标识，只要不和应用的其它通知的sId相同就行
            manager.notify(sId, notification);
        } else if (v == mButton2) {
            sId ++;
            //下面写法兼容性好
            Notification notification;
            //Android5.0以上以下属性无效
            //notification.icon = R.mipmap.ic_launcher;
            //notification.tickerText = "hello world";
            //notification.when = System.currentTimeMillis();
            //notification.flags = Notification.FLAG_AUTO_CANCEL;
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setContentTitle("title")
                    .setContentText("hello world")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.
                            decodeResource(getResources(),
                                    R.mipmap.ic_launcher)) // 创建通知的大图标
                    /*
                     * 首先，无论是使用自定义视图还是系统提供的视图，上面4的属性一定要设置，不然这个通知显示不出来
                     */
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setOngoing(false);
            notification = builder.build();
            Intent intent = new Intent(this, DemoActivity_1.class);
            intent.putExtra("sid", "" + sId);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            System.out.println(pendingIntent);
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
            remoteViews.setTextViewText(R.id.msg, "chapter_5: " + sId);
            remoteViews.setImageViewResource(R.id.icon, R.mipmap.icon1);
            PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(this,
                    0, new Intent(this, DemoActivity_2.class), PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
            notification.contentView = remoteViews;
            notification.contentIntent = pendingIntent;
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(sId, notification);
        }
    }

}
