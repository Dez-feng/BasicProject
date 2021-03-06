package com.example.dez.devlpart_contentprovider_book2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ProviderActivity extends AppCompatActivity {

    private static final String TAG = "ProviderActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri bookUri = Uri.parse("content://com.example.dez.devlpart_contentprovider_book2/book");
        ContentValues values = new ContentValues();

        values.put("_id",6);
        values.put("name","程序设计艺术");
        ContentResolver cr = getContentResolver();
                cr.insert(bookUri,values);

        Cursor bookCursor = getContentResolver().query(bookUri,null,null,null,null);
        while(bookCursor.moveToNext())
        {
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);

            Log.d(TAG,"query book:" + book);
        }
        bookCursor.close();

        Uri userUri = Uri.parse("content://com.example.dez.devlpart_contentprovider_book2/user");

        Cursor userCursor = getContentResolver().query(userUri,null,null,null,null);
        while(userCursor.moveToNext())
        {
            User user = new User();
            user.userId = userCursor.getInt(0);
            user.userName = userCursor.getString(1);
            user.isMale = userCursor.getInt(2) == 1;

            Log.d(TAG,"query user:" + user);
        }
        userCursor.close();

    }

}
