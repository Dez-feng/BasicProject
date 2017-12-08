package com.example.dez.thirdlibraryimport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mylibrary.maketoast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new maketoast().MakeToast(MainActivity.this);
    }
}
