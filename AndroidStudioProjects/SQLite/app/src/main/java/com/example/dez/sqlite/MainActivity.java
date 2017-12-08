package com.example.dez.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button1:    //创建数据库
                helper = new MySQLiteOpenHelper(this,"person.db");
                db = helper.getReadableDatabase();
                db.close();

                break;
            case R.id.button2:    //更新数据库
                helper = new MySQLiteOpenHelper(this,"person.db",2);
                db = helper.getReadableDatabase();
                db.close();

            case R.id.button3:   //增加
                helper = new MySQLiteOpenHelper(this,"person.db",2);

                db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name","xiaowu");
                db.insert("person",null,values);
                db.close();

                break;
            case R.id.button4:    //修改
                helper = new MySQLiteOpenHelper(this,"person.db",2);

                db = helper.getWritableDatabase();
                ContentValues values2 = new ContentValues();
                values2.put("name","zhangsan");
                db.update("person",values2,"_id = ?",new String[] {"1"});
                db.close();

                break;
            case R.id.button5:     //查询
                helper = new MySQLiteOpenHelper(this,"person.db",2);

                db = helper.getReadableDatabase();

                Cursor cursor = db.query("person",null,null,null,null,null,null);

               // Cursor  cursor2 = db.query("person", new String[]{"name"},
               // "_id > ?", new String[] {"5"}, null, null, null );

                while(cursor.moveToNext())
                {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));

                    Log.i("info-->","_id:" + id + " name: "+ name);
                }

                cursor.close();
                db.close();

            case R.id.button6:    //删除
                helper = new MySQLiteOpenHelper(this,"person.db",2);
                db = helper.getReadableDatabase();

                int count = db.delete("person", " _id = ?", new String[] {"5"});

                Log.i("info-->","删除条数:" + count);
                break;

            case R.id.button7:    //事务处理
                helper = new MySQLiteOpenHelper(this,"person.db",2);
                db = helper.getWritableDatabase();

                db.beginTransaction();
                for(int i = 0; i < 100; ++i)
                {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name","xiaowu" + i);

                    db.insert("person",null,contentValues);
                }

                Cursor cursor3 = db.query("person",null, null, null, null, null, null);
                while(cursor3.moveToNext())
                {
                    int id = cursor3.getInt(cursor3.getColumnIndex("name"));
                    String name = cursor3.getString(cursor3.getColumnIndex("name"));

                    Log.i("info-->","_id" + id + " name: " + name);
                }

                db.setTransactionSuccessful();

                db.endTransaction();

                cursor3.close();
                db.close();
        }




    }
}
