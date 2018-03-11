package com.example.dez.devlpart_socket_simplechatroom;

/**
 * Created by Dez on 2018/2/21.
 */

import java.io.Closeable;
import java.io.IOException;

public class MyUtils {

    public static void close(Closeable closeable){

        try{
            if(closeable != null)
            {
                closeable.close();
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}


