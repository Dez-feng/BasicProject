package com.example.dez.devlpaart_messengeractivity;

import android.os.Environment;

/**
 * Created by Dez on 2017/11/19.
 */

public class MyConstants {
    public static final String CHAPTER_2_PATH = Environment.getExternalStorageDirectory().getPath() + "/zhangmiao/charpter_2/";
    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;
}