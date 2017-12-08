package com.example.dez.broadcastreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity implements OnClickListener{


    private MyReceiver receiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onCreate------>","onCreate");
//
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.intent.action.MY_BROADCAST");
//
//        registerReceiver(receiver,filter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart------>","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume------>","onResume");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("onRestart------>","onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause------>","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop------>","onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
        Log.i("onDestroy------>","onDestroy");
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        intent.setAction("android.intent.action.MY_BROADCAST");

        intent.putExtra("msg","这是一条普通的消息");
        switch(v.getId())
        {
            case R.id.btn_send:
                sendOrderedBroadcast(intent,null);
            default:
                break;

        }

    }

}





