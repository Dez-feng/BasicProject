package com.example.dez.androidfilemanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.example.dez.androidfilemanager.R;
import com.example.dez.androidfilemanager.view.CustomViewPager;

import java.util.ArrayList;

public class ImagePreviewActivity extends AppCompatActivity {

    private LinearLayout barLayout;
    private boolean isShowBar = true;
    private Toolbar toolbar;
    private CustomViewPager viewPager;
    private int position = 0;
    private List<FileInfo> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
