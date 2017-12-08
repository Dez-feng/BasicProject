package com.example.helloanim;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.animation.*;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button tweenAnimButton;
	private ImageView tweenAnim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tweenAnimButton = (Button)this.findViewById(R.id.tweenAnim_btn);
		tweenAnimButton.setOnClickListener((OnClickListener) this);
		tweenAnim = (ImageView)this.findViewById(R.id.tween_anim);
		
	}

	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg)
		{
			if(msg.what == 0x123){
				Animation reverseAnim = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.tween_anim_reverse);
				tweenAnim.startAnimation(reverseAnim);
				
			}
			
		}
		
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void loadFirstTweenAnim()
	{
		Animation animation = AnimationUtils.loadAnimation(this,R.anim.tween_anim);
		
		animation.setFillAfter(true);
		
		tweenAnim.startAnimation(animation);
		
		//设置0.5后启动第二个布局动画
		new Timer().schedule(new TimerTask(){
		
		@Override
		public void run(){
			handler.sendEmptyMessage(0x123);
			
		}
				
		}, 500);
		
		
	}
	
	
}
