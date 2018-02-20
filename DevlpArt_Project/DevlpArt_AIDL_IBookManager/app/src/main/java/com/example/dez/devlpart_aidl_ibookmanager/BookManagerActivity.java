package com.example.dez.devlpart_aidl_ibookmanager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {

    private static final String TAG = "BMA";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
    private IBookManager mRemoteBookManager;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch(msg.what)
            {
                case MESSAGE_NEW_BOOK_ARRIVED:
                Log.d(TAG,"receive new book:" + msg.obj);
                default:
                super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try{
                mRemoteBookManager = bookManager;
                List<Book> bookList = bookManager.getBookList();
                Log.i(TAG,"query book list,list type:" + bookList.getClass().getCanonicalName());
                Log.i(TAG,"query book list:" + bookList.toString());

                Book newBook = new Book(3,"Android开发艺术探索");
                bookManager.addBook(newBook);
                Log.i(TAG,"add book:" + newBook);

                List<Book> newList = bookManager.getBookList();
                Log.i(TAG,"query book list:" + newList.toString());

                Log.i(TAG,name.getClassName());

                bookManager.registerListener(mOnNewBookArrivedListener);
            }
            catch(RemoteException e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            mRemoteBookManager = null;
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub(){

        @Override
        public void OnNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED,newBook).sendToTarget();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

        Intent intent = new Intent(this,BookManagerService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);

    }


    @Override
    protected void onDestroy() {

        if(mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive())
        {
            try {
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
                Log.d(TAG,"unregister listener:" + mOnNewBookArrivedListener);
            }catch(RemoteException e)
            {
                e.printStackTrace();
            }


        }
        unbindService(connection);
        super.onDestroy();
    }

}
