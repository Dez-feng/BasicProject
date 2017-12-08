package com.example.dez.appconnectnetwork;

import java.io.File;
import java.util.List;


import android.os.Environment;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.dez.appconnectnetwork.adapter.ContactAdapter;
import com.example.dez.appconnectnetwork.domain.Contact;
import com.example.service.ContactService;

public class MainActivity extends AppCompatActivity {

    ListView listView = null;
    File cache;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            listView.setAdapter(new ContactAdapter(MainActivity.this,
                    (List<Contact>)msg.obj, R.layout.list_item_layout, cache));
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)this.findViewById(R.id.listView);


        cache = new File(Environment.getExternalStorageDirectory(),"cache");

        if(!cache.exists())
            cache.mkdirs();



        new Thread(new Runnable(){
            @Override
            public void run() {

                try{

                    List<Contact> data = ContactService.getContacts();
                    handler.sendMessage(handler.obtainMessage(22,data));
                }
                catch(Exception e)
                {
                    Log.e("info",Log.getStackTraceString(e));
                }

            }
        }).start();



    }

    @Override
    protected void onDestroy() {


        for(File file:cache.listFiles())
        {
            file.delete();
        }
        cache.delete();
        super.onDestroy();
    }



}








