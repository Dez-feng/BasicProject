package com.example.dez.test_singletask_thrtimesclick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MainActivity.class);

                intent.putExtra("time",System.currentTimeMillis());

                startActivity(intent);


            }
        });

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,"onNewIntent, time = " + intent.getLongExtra("time",0));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"omPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"omResume");
    }


}
