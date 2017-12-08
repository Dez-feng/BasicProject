package com.example.dez.jsonparse;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.button1:
                //The one method
                JSONObject person = new JSONObject();
                try{
                    person.put("name","zhangsan");
                    person.put("age",10);
                    JSONArray phones = new JSONArray();
                    phones.put("15998589231").put("15329297323");
                    person.put("phone",phones);

                    String jsonData = person.toString();
                    Log.i("info",jsonData);
                }catch(JSONException e)
                {
                    Log.e("info",Log.getStackTraceString(e));
                }

                String s = "";

                //The two method
                try {
                    JSONStringer jsonStringer = new JSONStringer();

                    jsonStringer.object();
                    jsonStringer.key("name").value("zhangsan");
                    jsonStringer.key("age").value(19);

                    jsonStringer.key("phones");
                    jsonStringer.array();
                    jsonStringer.value("15998589231").value("15329297323");
                    jsonStringer.endArray();

                    jsonStringer.endObject();

                    s = jsonStringer.toString();
                    Log.i("info", s);
                }catch(JSONException e)
                {
                    Log.e("info",Log.getStackTraceString(e));
                }

                //对json字符串进行解析
                try{

                    JSONObject jsonObject = new JSONObject(s);
                    String name = jsonObject.getString("name");
                    int age = jsonObject.getInt("age");
                    JSONArray array = jsonObject.getJSONArray("phones");
                    String phone1 = array.getString(0);
                    String phone2 = array.getString(1);
                    List<String> numbers = new ArrayList<String>();
                    numbers.add(phone1);
                    numbers.add(phone2);

                    Person p = new Person(name, age, numbers);

                    Log.i("person", p.getName()+"/"+p.getAge()+"/"+phone1+"/"+phone2);



                }catch(JSONException e){
                    Log.e("info",Log.getStackTraceString(e));

                }

                break;

            default:
                break;

        }
    }



}
