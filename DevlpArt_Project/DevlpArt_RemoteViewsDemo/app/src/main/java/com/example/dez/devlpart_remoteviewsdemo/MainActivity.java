package com.example.dez.devlpart_remoteviewsdemo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.example.dez.devlpart_remoteviewsdemo.utils.MyConstants;

@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout mRemoteViewsContent;
    private RemoteViewsReceiver mRemoteViewsReceiver;
    //interface用private不能被继承和实例化
    public interface Callback{
        void updateUI(RemoteViews remoteViews);
    }
    //以下代码用于同一APP同一组件的通讯
    //定义MainActivity的广播接收器mRemoteViewsReceiver
    //private BroadcastReceiver mRemoteViewsReceiver = new BroadcastReceiver() {
    //    @Override
    //    public void onReceive(Context context, Intent intent) {
    //        Log.d(TAG,"onReceive");
    //        if(intent.getAction().equals("com.example.dez.devlpart_remoteviewsdemo.action_REMOTE")) {
    //            RemoteViews remoteViews = intent
    //                    .getParcelableExtra(MyConstants.EXTRA_REMOTE_VIEWS);
    //            if (remoteViews != null) {
    //                updateUI(remoteViews);
    //            }
    //        }
    //    }
    //};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRemoteViewsContent = (LinearLayout) findViewById(R.id.remote_views_content);
        mRemoteViewsReceiver = new RemoteViewsReceiver();
        //new Callback是匿名内部类
        mRemoteViewsReceiver.setCallback(new Callback(){

            @Override
            public void updateUI(RemoteViews remoteViews) {
                //layoutId = R.layout.layout_simulated_notification
                    int layoutId = getResources().getIdentifier("layout_simulated_notification", "layout", getPackageName());
                    View view = getLayoutInflater().inflate(layoutId, mRemoteViewsContent, false);
                //将DemoActivity对View所作的一系列更新全部作用到MainActivity中加载的View上
                    remoteViews.reapply(MainActivity.this, view);
                    mRemoteViewsContent.addView(view);
                //这种写法适用于同一应用多进程的情况下
                //View view = remoteViews.apply(this, mRemoteViewsContent);
                //mRemoteViewsContent.addView(view);
            }
        });
        IntentFilter filter = new IntentFilter();
        filter.addAction(MyConstants.REMOTE_ACTION);
        registerReceiver(mRemoteViewsReceiver, filter);
    }

    //private void updateUI(RemoteViews remoteViews) {

        //这种写法适用于不同应用多进程的情况下
        //因为B中的布局文件的id传输到A中以后很可能是无效的，资源id不一样
        //layoutId = R.layout.layout_simulated_notification
    //    int layoutId = getResources().getIdentifier("layout_simulated_notification", "layout", getPackageName());
    //    View view = getLayoutInflater().inflate(layoutId, mRemoteViewsContent, false);
        //将DemoActivity对View所作的一系列更新全部作用到MainActivity中加载的View上
    //    remoteViews.reapply(this, view);
    //    mRemoteViewsContent.addView(view);

        //这种写法适用于同一应用多进程的情况下
        //View view = remoteViews.apply(this, mRemoteViewsContent);
        //mRemoteViewsContent.addView(view);
    //}

    @Override
    protected void onDestroy() {
        unregisterReceiver(mRemoteViewsReceiver);
        super.onDestroy();
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, DemoActivity_2.class);
            startActivity(intent);
        }
    }

}
