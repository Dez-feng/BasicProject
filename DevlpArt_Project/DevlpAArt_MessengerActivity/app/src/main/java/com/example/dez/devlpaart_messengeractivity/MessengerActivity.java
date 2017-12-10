package com.example.dez.devlpaart_messengeractivity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Dez on 2017/11/19.
 */

public class MessengerActivity extends Activity {

    private static final String Tag = "MessengerActivity";

    private Messenger mService;

    private ServiceConnection mConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = Message.obtain(null,MyConstants.MSG_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg","This is client.");
            msg.setData(bundle);

            msg.replyTo = mGetReplyMessenger;
            try {
                mService.send(msg);
                Log.d(Tag,"senddata");

            }
            catch(RemoteException e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {

            switch(msg.what)
            {
                case MyConstants.MSG_FROM_SERVICE:
                    Log.i(Tag,"receive msg from Service:" + msg.getData().getString("reply"));
                    break;
                default:super.handleMessage(msg);
            }

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this,MessengerService.class);

        bindService(intent,mConnection,Service.BIND_AUTO_CREATE);


    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}