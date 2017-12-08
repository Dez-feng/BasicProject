package com.example.dez.animation;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)this.findViewById(R.id.textview);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                Animation animation = AnimationUtils.loadAnimation(this,
                        R.anim.alpha);
                textView.startAnimation(animation);
                break;

            case R.id.button2:
                Animation animation2 = AnimationUtils.loadAnimation(this,
                        R.anim.translate);
                textView.startAnimation(animation2);
                break;

            case R.id.button3:
                Animation animation3 = AnimationUtils.loadAnimation(this,
                        R.anim.scale);
                textView.startAnimation(animation3);
                break;

            case R.id.button4:
                Animation animation4 = AnimationUtils.loadAnimation(this,
                        R.anim.rotate);
                textView.startAnimation(animation4);
                break;

            case R.id.button5:
                Animation animation5 = AnimationUtils.loadAnimation(this,
                        R.anim.set);
                textView.startAnimation(animation5);
                break;

            default:
                break;

        }

    }






}




