package com.example.dez.file;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.IOException;
import android.os.Environment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File file = new File(getFilesDir(),"example.txt");
        try{

            file.createNewFile();
        }catch(IOException e)
        {
            e.printStackTrace();
        }


        File file2 = new File(getCacheDir(),"example2.txt");
        try{

            file2.createNewFile();
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        File file3 = new File(Environment.getExternalStorageDirectory(),"example3.txt");
        try{

            file3.createNewFile();
        }catch(IOException e)
        {
            e.printStackTrace();
        }




    }
}






