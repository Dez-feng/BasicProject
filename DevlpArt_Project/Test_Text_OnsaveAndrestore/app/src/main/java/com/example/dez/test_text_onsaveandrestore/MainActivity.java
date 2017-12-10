package com.example.dez.test_text_onsaveandrestore;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
        {
            String test = savedInstanceState.getString("extra_test");
            Log.d("MainActivity", "[onCreate]restore extra_test: " + test);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("extra_test","test");
        super.onSaveInstanceState(outState);
        Log.d("MainActivity","[onSaveInstanceState]store extra_test");

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String test = savedInstanceState.getString("extra_test");
        Log.d("MainActivity","[onRestoreInstancecState]restore extra_test: " + test);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("MainActivity","onConfigurationChanged, newOrientation: " + newConfig.orientation);

    }
}
