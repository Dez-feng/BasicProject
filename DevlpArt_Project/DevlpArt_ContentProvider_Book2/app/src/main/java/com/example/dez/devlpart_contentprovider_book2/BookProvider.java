package com.example.dez.devlpart_contentprovider_book2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Dez on 2018/1/17.
 */

public class BookProvider extends ContentProvider {

    private static final String TAG = "BookProvider";

    private static final String AUTHORITY = "com.example.dez.devlpart_contentprovider_book2";

    private static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    private static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    private static final int BOOK_URI_CODE = 0;
    private static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        sUriMatcher.addURI(AUTHORITY,"book",BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY,"user",USER_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDB;

    @Override
    public boolean onCreate() {

        Log.d(TAG,"onCreate, current thread:" + Thread.currentThread().getName());
        mContext = getContext();
        initProviderData();
        return true;
    }

    private void initProviderData(){

        mDB = new DbOpenHelper(mContext).getWritableDatabase();

        mDB.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        mDB.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        mDB.execSQL("insert into book values(3,'Android')");
        mDB.execSQL("insert into book values(4,'Ios')");
        mDB.execSQL("insert into book values(5,'Html5')");
        mDB.execSQL("insert into user values(1,'jake',1)");
        mDB.execSQL("insert into user values(2,'jasmine',0)");

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Log.d(TAG,"query, current thread:" + Thread.currentThread().getName());

        String table = getTableName(uri);
        if(table == null)
            throw new IllegalArgumentException("Unsupported Uri: " + uri);
        return mDB.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Log.d(TAG,"insert, current thread:" + Thread.currentThread().getName());

        String table = getTableName(uri);
        if(table == null)
            throw new IllegalArgumentException("Unsupported Uri: " + uri);
        mDB.insert(table,null,values);
        mContext.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        Log.d(TAG,"delete, current thread:" + Thread.currentThread().getName());

        String table = getTableName(uri);
        if(table == null)
            throw new IllegalArgumentException("Unsupported Uri: " + uri);
        int count = mDB.delete(table,selection,selectionArgs);
        if(count > 0)
            mContext.getContentResolver().notifyChange(uri,null);

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        Log.d(TAG,"update, current thread:" + Thread.currentThread().getName());

        String table = getTableName(uri);
        if(table == null)
            throw new IllegalArgumentException("Unsupported Uri: " + uri);
        int row = mDB.update(table,values,selection,selectionArgs);
        if(row > 0)
            mContext.getContentResolver().notifyChange(uri,null);

        return row;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        Log.d(TAG,"getType");
        return null;
    }

    private String getTableName(Uri uri)
    {
        String tableName = null;

        switch(sUriMatcher.match(uri))
        {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }

        return tableName;
    }




}
