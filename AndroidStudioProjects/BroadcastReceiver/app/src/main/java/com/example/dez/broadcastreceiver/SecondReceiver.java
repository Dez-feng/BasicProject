package com.example.dez.broadcastreceiver;

import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Dez on 2017/5/26.
 */

public class SecondReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        //先获得广播过来的MSG
        String broadcast_msg = intent.getStringExtra("msg");
        Log.i(TAG, "SecondRecever--broadcast_msg："+broadcast_msg);

        //接收通过setResultExtras传过来的msg
        String msg = getResultExtras(true).getString("msg");
        Log.i(TAG, "SecondReceiver: " + msg);

        //修改setResultExtras传来的结果
        Bundle bundle = new Bundle();
        bundle.putString("msg", msg + "@SecondReceiver");
        setResultExtras(bundle);
    }
}
