package com.example.dez.viewpage;

import java.util.List;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)this.findViewById(R.id.viewpager);
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.view,null));
        views.add(inflater.inflate(R.layout.view2,null));
        views.add(inflater.inflate(R.layout.view3,null));

        adapter = new MyViewPagerAdapter(views);

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, "页面" + position, Toast.LENGTH_SHORT).show();
                
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });



    }
}
















