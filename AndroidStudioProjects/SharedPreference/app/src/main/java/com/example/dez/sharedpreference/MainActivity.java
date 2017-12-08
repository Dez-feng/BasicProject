package com.example.dez.sharedpreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private SharedPreferences preferences;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)this.findViewById(R.id.editText1);

        textView = (TextView)this.findViewById(R.id.textview);


        preferences = this.getSharedPreferences("dez",MODE_PRIVATE);
        textView.setText(preferences.getString("key","初始字符串"));
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button1:
                String str = editText.getText().toString();
                Editor editor = preferences.edit();

                editor.putString("key",str);

                editor.commit();




        }

    }


}

