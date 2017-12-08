package com.example.dez.myservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        Log.i("onStart------>","onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("onStart------>","onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.i("onStart------>","onReStart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i("onStart------>","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("onStart------>","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("onStart------>","onDestroy");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(MainActivity.this,com.example.dez.myservice.MyService.class);

        switch (v.getId()) {
            case R.id.btn_start_service:
                startService(intent);
                break;
            case R.id.btn_stop_service:
                stopService(intent);
                break;
            default:
                break;
        }
    }


}
