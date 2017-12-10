package com.example.dez.test_threeprocess;

import android.app.Application;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Created by Dez on 2017/10/18.
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    String processName = null;


    @Override
    public void onCreate() {
        super.onCreate();

        new Thread() {
            @Override
            public void run() {
                super.run();
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/cmdline"));
                    processName = reader.readLine();

                    if (!TextUtils.isEmpty(processName)) {
                        processName = processName.trim();
                    }

                } catch (Exception e) {
                    Log.e(TAG, "processName read is fail. exception=" + e);
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "processName close is fail. exception" + e);
                    }
                }
            }

        }.start();

        Log.d(TAG, "application start, process name:" + processName);

    }


}