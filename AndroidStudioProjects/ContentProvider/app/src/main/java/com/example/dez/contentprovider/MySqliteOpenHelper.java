package com.example.dez.contentprovider;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dez on 2017/5/31.
 */

public class MySqliteOpenHelper extends SQLiteOpenHelper{

    public static final String DBNAME = "book.db";
    public static final String TABLENAME = "booktbl";

    public MySqliteOpenHelper(Context context)
    {
        super(context,DBNAME,null,1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "
                + TABLENAME
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                  "name TEXT NOT NULL, "+
                  "price FLOAT NOT NULL, "+
                  "publisher TEXT NOT NULL )";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("name","Android入门实战");
        values.put("price",30.0);
        values.put("publisher","CSDN学院出版社");
        db.insert(TABLENAME,null,values);
        values.clear();

        values.put("name","Android高级编程");
        values.put("price",50.0);
        values.put("publisher","CSDN学院出版社");
        db.insert(TABLENAME,null,values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}




