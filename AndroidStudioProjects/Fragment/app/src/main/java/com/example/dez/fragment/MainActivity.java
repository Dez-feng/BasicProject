package com.example.dez.fragment;

import android.support.v7.app.AppCompatActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    private MyFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new MyFragment();

        fragmentManager = getFragmentManager();

        transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.content_layout, fragment);

        transaction.commit();

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.content_layout, new MyFragment2());

                transaction.commit();
                break;

            case R.id.button2:
                transaction = fragmentManager.beginTransaction();

                transaction.remove(fragment);

                transaction.commit();
                break;

            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }
}













