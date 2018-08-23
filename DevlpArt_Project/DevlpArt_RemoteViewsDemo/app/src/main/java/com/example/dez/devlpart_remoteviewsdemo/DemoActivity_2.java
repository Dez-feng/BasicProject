package com.example.dez.devlpart_remoteviewsdemo;

import com.example.dez.devlpart_remoteviewsdemo.utils.MyConstants;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class DemoActivity_2 extends Activity {
    private static final String TAG = "DemoActivity_2";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_2);
        Log.d(TAG, "onCreate");
        Toast.makeText(this, getIntent().getStringExtra("sid"),
                Toast.LENGTH_SHORT).show();
        initView();
    }

    private void initView() {
    }


    public void onButtonClick(View v) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_simulated_notification);
        remoteViews.setTextViewText(R.id.msg, "msg from process:" + Process.myPid());
        //1.remoteViews.setImageViewResource(R.id.icon, R.mipmap.icon1);
        //2.BitmapFactory是一个工具类，它提供了大量的方法来用于从不同的数据源来解析、创建Bitmap对象
        //Bitmap bmp = BitmapFactory.decodeResource(this.getResources(),R.id.icon);
        //remoteViews.setImageViewBitmap(R.id.icon,bmp);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, new Intent(this, DemoActivity_1.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, DemoActivity_2.class), PendingIntent.FLAG_UPDATE_CURRENT);
        //点击R.id.item_holder触发pendingIntent，点击R.id.open_activity2触发openActivity2PendingIntent
        remoteViews.setOnClickPendingIntent(R.id.item_holder, pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
        Intent intent = new Intent("com.example.dez.devlpart_remoteviewsdemo.action_REMOTE");
        intent.putExtra(MyConstants.EXTRA_REMOTE_VIEWS, remoteViews);
        sendBroadcast(intent);
    }

}
