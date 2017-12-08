package com.example.dez.myservice;

/**
 * Created by Dez on 2017/5/24.
 */

import android.app.Service;
import android.os.IBinder;
import android.util.Log;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

public class MyService extends Service {



    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("onCreate------>","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("onStartCommend------>","onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("IBinder------>","IBinder");
        return null;
    }



}


