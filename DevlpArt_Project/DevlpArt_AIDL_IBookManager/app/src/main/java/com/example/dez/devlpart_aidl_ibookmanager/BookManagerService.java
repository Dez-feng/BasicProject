package com.example.dez.devlpart_aidl_ibookmanager;

import android.app.Service;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Dez on 2017/12/17.
 */


public class BookManagerService extends Service {

    private static final String TAG = "BMS";

    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<IOnNewBookArrivedListener>();
    private Binder mBinder = new IBookManager.Stub(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {

            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            /*
            if(!mListenerList.contains(listener))
            {
                mListenerList.add(listener);
                Log.d(TAG,"register listener succeed.");
            }
            else
            {
                Log.d(TAG,"already exists.");
            }

            Log.d(TAG,"registerListener size:" + mListenerList.size());
            */

            mListenerList.register(listener);
            Log.d(TAG,"registerListener size:" + mListenerList.beginBroadcast());
            mListenerList.finishBroadcast();
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            /*
            if(mListenerList.contains(listener))
            {
                mListenerList.remove(listener);
                Log.d(TAG,"unregister listener succeed.");
            }
            else
            {
                Log.d(TAG,"not found,can not unregister.");
            }
            */

            mListenerList.unregister(listener);

            Log.d(TAG,"unregisterListener, current size:" + mListenerList.beginBroadcast());
            mListenerList.finishBroadcast();
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int Flags) throws RemoteException{

            int check = checkCallingOrSelfPermission(
                 "com.example.dez.devlpart_aidl_ibookmanager.permission.ACCESS_BOOK_SERVICE");
            if(check == PackageManager.PERMISSION_DENIED)
            {
                return false;
            }

            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());

            //检验信息是否有效
            if(packages != null && packages.length > 0)
            {
                //将有用信息取出
                packageName = packages[0];
            }

            //验证有用信息
            if(!packageName.startsWith("com.example.dez"))
                return false;

            return super.onTransact(code,data,reply,Flags);
        }

    };



    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1,"Android"));
        mBookList.add(new Book(2,"Ios"));

        new Thread(new ServiceWorker()).start();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.example.dez.devlpart_aidl_ibookmanager.permission.ACCESS_BOOK_SERVICE");

        if(check == PackageManager.PERMISSION_DENIED)
        {
            return null;
        }

        return mBinder;
    }

    @Override
    public void onDestroy() {

        mIsServiceDestroyed.set(true);
        super.onDestroy();
    }

    private void OnNewBookArrived(Book newBook) throws RemoteException
    {
        mBookList.add(newBook);
        /*
        Log.d(TAG,"OnNewBookArrived,notify listeners:" + mListenerList.size());

        for(int i = 0;i < mListenerList.size();++i)
        {
            IOnNewBookArrivedListener listener = mListenerList.get(i);
            Log.d(TAG,"OnNewBookArrived,notify listener:" + listener);

            //一出异常就终止方法
            listener.OnNewBookArrived(newBook);
        }
        */

        final int N = mListenerList.beginBroadcast();

        for(int i = 0;i < N;++i)
        {
            IOnNewBookArrivedListener l = mListenerList.getBroadcastItem(i);
            if(l != null) {
                try {
                    l.OnNewBookArrived(newBook);

                }catch(RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        }

        mListenerList.finishBroadcast();


    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {

            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try {
                    OnNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }

    }



}
