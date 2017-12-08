package com.example.dez.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dez on 2017/6/2.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    public MySQLiteOpenHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    public MySQLiteOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE person ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL )";
        db.execSQL(sql);

        Log.i("onCreate", "创建数据库");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("onUpgrade","更新数据库");
    }
}
