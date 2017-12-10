package com.example.dez.devlpart_messengerservice;

import android.app.Service;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.Messenger;
import android.os.Message;
import android.os.Environment;
import android.support.annotation.Nullable;
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
                    new AlertDialog.Builder(MainActivity.getMainActivity().getBaseContext())
                            .setTitle("Toast")
                            .setMessage(msg.getData().getString("msg"))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                    break;
                default:

                    super.handleMessage(msg);
            }


        }
    }

    private Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mMessenger.getBinder();
    }
}







