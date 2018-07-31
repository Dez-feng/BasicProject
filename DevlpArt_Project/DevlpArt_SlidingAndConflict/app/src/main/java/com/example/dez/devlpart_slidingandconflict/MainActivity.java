package com.example.dez.devlpart_slidingandconflict;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v)
    {
        switch(v.getId()){
            case R.id.button1: {
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button2: {
                Intent intent = new Intent(this,DemoActivity_1.class);
                startActivity(intent);
                break;
            }
            case R.id.button3:{
                Intent intent = new Intent(this,DemoActivity_2.class);
                startActivity(intent);
                break;
            }

        }

    }

}
