package com.example.dez.broadcastreceiver;


import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.util.Log;

/**
 * Created by Dez on 2017/5/26.
 */

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        String msg = getResultExtras(true).getString("msg");
        Log.i(TAG, "MyReceiver: " + msg);
    }
}











