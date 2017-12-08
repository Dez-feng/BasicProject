package com.example.dez.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    listView = (ListView)this.findViewById(R.id.listview);

    List<Map<String,String>> data = new ArrayList<Map<String,String>>();
    for(int i = 0;i < 20;++i) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("TITLE", "列表项测试" + i);
        data.add(map);
    }
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,data,
                android.R.layout.simple_list_item_1,new String[]{"TITLE"},
                new int[] {android.R.id.text1} );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"列表项"+position,Toast.LENGTH_SHORT).show();
            }
        });

    }


}

