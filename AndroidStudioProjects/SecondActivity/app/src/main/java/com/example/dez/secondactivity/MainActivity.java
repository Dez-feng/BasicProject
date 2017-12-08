package com.example.dez.secondactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void implicitIntent(){
        Intent intent = new Intent();
        intent.setAction("com.example.dez.action");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.parse("xiaowei://www.baidu.com/person"),"person/people");

        startActivity(intent);
        return;

    }




    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.button1:
//                Intent intent = new Intent(MainActivity.this,SecondaryActivity.class);
//                startActivity(intent);
                implicitIntent();
                break;
            default:
                break;

        }

    }
}
