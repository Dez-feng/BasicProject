package com.example.dez.myservice;

/**
 * Created by Dez on 2017/5/24.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BindService extends Service {


    private int count=0;
    private boolean quit;

    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder{
        public int getCount(){
            //获取Service的运行状态
            return count;
        }
    }

    @Override
    public void onCreate() {
        this.quit=false;
        super.onCreate();
        Log.i("onCreate------>","onCreate");

        new Thread(){
            public void run(){
                while(!quit){
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    count++;
                }
            }

        }.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("onStartCommend------>","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("Service is Unbinded");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        Log.i("onDestroy","onDestroy");
        System.out.println("Service is Destroyed");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("IBinder------>","IBinder");
        System.out.println("Service is Binded");
        return binder;
    }

}
