package com.example.dez.devlpart_circleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,DemoActivity_1.class);
            startActivity(intent);
        }

        return super.onTouchEvent(event);
    }

    /*
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn1)
        {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,DemoActivity_1.class);
            startActivity(intent);
        }
    }
    */


}
