package com.example.dez.myservice;

import android.content.ComponentName;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ServiceConnection;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import static android.widget.Toast.LENGTH_LONG;


public class BindServiceActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
    }

    BindService.MyBinder binder;

    private ServiceConnection conn = new ServiceConnection(){

        //当activity与客户端连接时回调此方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("--Service Connected--");
            binder = (BindService.MyBinder)service;
        }

        //当activity与客户端断开连接时回调此方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("--Service Connected--");
        }

    };

    @Override
    public void onClick(View v) {

        final Intent intent = new Intent(BindServiceActivity.this,com.example.dez.myservice.BindService.class);
        switch(v.getId()){
            case R.id.btn_bind_service:
                bindService(intent,conn,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(conn);
                break;
            case R.id.btn_get_status:
                Toast.makeText(BindServiceActivity.this,"Service的count值为："+binder.getCount(),LENGTH_LONG).show();
                break;
            default:
                break;

        }



    }
}
