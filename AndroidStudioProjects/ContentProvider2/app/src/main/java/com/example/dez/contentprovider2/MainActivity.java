package com.example.dez.contentprovider2;

import android.util.Log;
import android.net.Uri;
import android.view.View;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("content://com.csdn.providers.book/book");
        ContentValues values = new ContentValues();
        values.put("name","java讲义");
        values.put("price",50.0);
        values.put("publisher","CSDN出版社");
        Uri retUri = getContentResolver().insert(uri,values);
        long id = ContentUris.parseId(retUri);

        Log.i("parseId","输入的id为: "+id);

        Cursor c = getContentResolver().query(uri,null,null,null,null);

        if(c != null) {
            while (c.moveToNext()) {
                String[] cols = c.getColumnNames();

                for (String col : cols) {
                    Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
                }

                Log.i("info", "++++++++++++++++");

            }

        }


    }

    @Override
    public void onClick(View v) {

    }
}






