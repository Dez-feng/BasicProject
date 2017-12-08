package com.example.dez.helloworld;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button tweenAnimButton;
    private ImageView tweenAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tweenAnimButton = (Button) findViewById(R.id.btn_play_tween_anim);

        tweenAnim = (ImageView)findViewById(R.id.tween_anim);

    }

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 0x123){
                Animation reverseAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tween_anim_reverse);
                tweenAnim.startAnimation(reverseAnim);
            }

        }
    };

    public void loadFirstTweenAnim(View view){
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.tween_anim);
        animation.setFillAfter(true);

        tweenAnim.startAnimation(animation);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        },500);

    }


}
