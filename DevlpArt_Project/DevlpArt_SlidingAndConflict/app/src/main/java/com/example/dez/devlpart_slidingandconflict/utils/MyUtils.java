package com.example.dez.devlpart_slidingandconflict.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.view.WindowManager;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Dez on 2018/7/16.
 */

public class MyUtils {

    public static String getProcessName(Context cxt,int pid)
    {
        ActivityManager am = (ActivityManager)cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();

        if(runningApps == null)
            return null;

        for(RunningAppProcessInfo procInfo: runningApps)
        {
            //通过pid属性确定是否是同一进程，再获取目标属性processName
            if(procInfo.pid == pid)
            {
                return procInfo.processName;
            }
        }

        return null;
    }

    public static void close(Closeable closeable)
    {
        try{
            if(closeable != null)
                closeable.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static DisplayMetrics getScreenMetrics(Context context)
    {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm); //Display.getMetrics(DisplayMetrics outMetrics)
        return dm;

    }

    public static void executeInThread(Runnable runnable)
    {
        new Thread(runnable).start();
    }


}
