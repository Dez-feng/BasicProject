package com.example.dez.devlpart_remoteviewsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.dez.devlpart_remoteviewsdemo.utils.MyConstants;

//以下代码用于整个APP多进程不同组件的通讯
public class RemoteViewsReceiver extends BroadcastReceiver{

    private static final String TAG = "RemoteViewsReceiver";
    private MainActivity.Callback callback;

    public void setCallback(MainActivity.Callback callback){
        this.callback = callback;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG,"onReceive");
               if(intent.getAction().equals(MyConstants.REMOTE_ACTION)) {
                   RemoteViews remoteViews = intent
                            .getParcelableExtra(MyConstants.EXTRA_REMOTE_VIEWS);
                    if (remoteViews != null && callback != null) {
                        callback.updateUI(remoteViews);
                    }
                }
    }

}
