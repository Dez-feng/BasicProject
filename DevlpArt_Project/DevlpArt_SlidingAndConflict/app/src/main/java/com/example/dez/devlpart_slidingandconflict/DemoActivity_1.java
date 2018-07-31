package com.example.dez.devlpart_slidingandconflict;

import android.app.Activity;

/**
 * Created by Dez on 2018/5/18.
 */
import java.util.ArrayList;
import com.example.dez.devlpart_slidingandconflict.R;
import com.example.dez.devlpart_slidingandconflict.utils.MyUtils;
import com.example.dez.devlpart_slidingandconflict.ui.HorizontalScrollViewEx;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DemoActivity_1 extends Activity {
    private static final String TAG = "DemoActivity_1";

    private HorizontalScrollViewEx mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_1);
        Log.d(TAG, "onCreate");
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();  //LayoutInflater将Layout文件夹的xml文件转为View
        mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);  //findViewById在当前View的布局文件中寻找组件
        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;    //获取屏幕宽度
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;  //获取屏幕高度
        for (int i = 0; i < 3; i++) {
            //将R.layout.content_layout转为view,并通过mListContainer生成布局参数,不以mListContainer为父容器
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.content_layout, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout); //只能添加没有父容器的View
        }
    }

    private void createList(ViewGroup layout) {
        //1.创建承载数据的视图容器
        ListView listView = (ListView) layout.findViewById(R.id.list);
        //2..创建数据源
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }
        //3.创建适配器并绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.content_list_item, R.id.name, datas);
        //4.为ListView容器添加适配器
        listView.setAdapter(adapter);
        //5.设置点击事件
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(DemoActivity_1.this, "click item",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}