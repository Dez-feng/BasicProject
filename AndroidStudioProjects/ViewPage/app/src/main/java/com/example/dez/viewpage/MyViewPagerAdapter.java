package com.example.dez.viewpage;

import java.util.List;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Dez on 2017/6/20.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private List<View> views;

    public MyViewPagerAdapter(List<View> views){
        super();
        this.views = views;

    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);

    }

    //判断对象生成界面
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));

    }


}







