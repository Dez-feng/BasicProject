package com.example.dez.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Dez on 2017/5/31.
 */

public class BookContentProvider extends ContentProvider {
    private static UriMatcher matcher;
    private static final String TABLENAME = "booktbl";
    private MySqliteOpenHelper helper;

    //添加规则
    static{
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI("com.csdn.providers.book","book",1);    //多值查询
        matcher.addURI("con.csdn.providers.book","book/#",2);      //单值查询
    }



    @Override
    public boolean onCreate() {
        helper = new MySqliteOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        switch(matcher.match(uri)){
            case 1:
                cursor = db.query(TABLENAME,null,null,null,null,null,null);
                break;
            case 2:
                break;
            default:
                break;

        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        SQLiteDatabase db = helper.getWritableDatabase();

        long rowid = db.insert(TABLENAME,null,values);

        Uri retUri = ContentUris.withAppendedId(uri,rowid);

        return retUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
