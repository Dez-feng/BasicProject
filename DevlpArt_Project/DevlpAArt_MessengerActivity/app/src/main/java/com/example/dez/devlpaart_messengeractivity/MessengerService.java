package com.example.dez.devlpaart_messengeractivity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.text.LoginFilter;
import android.util.Log;

/**
 * Created by Dez on 2017/11/19.
 */

public class MessengerService extends Service {

    private static final String Tag = "MessengerService";



    private static class MessengerHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {

            switch(msg.what){
                case MyConstants.MSG_FROM_CLIENT:
                    Log.d(Tag,"receive msg from Client:" + msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replyMessage = Message.obtain(null,MyConstants.MSG_FROM_SERVICE);

                    Bundle bundle = new Bundle();
                    bundle.putString("reply","嗯，你的消息我已经收到，稍后会回复你。");
                    replyMessage.setData(bundle);
                    try{
                        client.send(replyMessage);

                    }catch(RemoteException e)
                    {
                        e.printStackTrace();
                    }



                    break;
                default:

                    super.handleMessage(msg);
            }


        }
    }
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
    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mMessenger.getBinder();
    }
}







