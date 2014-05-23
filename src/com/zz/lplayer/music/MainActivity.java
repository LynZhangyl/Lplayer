package com.zz.lplayer.music;

import com.zz.lplayer.dao.latestdao;
import com.zz.lplayer.music.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;



public class MainActivity extends TabActivity {

	
	private TabSpec spec;
	private TabHost tabHost;
	private Intent intent;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	int currentView = 0;
	private static int maxTabIndex = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Log.v("test","MainActivity OnCreate");
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);// 用于全屏显示
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
 //   	setContentView(R.layout.main);
		latestdao latestdao1 = new latestdao(getApplicationContext());
		
	
		
		Resources res = getResources(); 
        tabHost = getTabHost(); 
       // TabHost.TabSpec spec; 
       // Intent intent;  
        
        intent = new Intent().setClass(this, LatestActivity.class);
        spec = tabHost.newTabSpec("最近播放").setIndicator("最近播放",
                          res.getDrawable(R.drawable.item))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, SongsActivity.class);
        spec = tabHost.newTabSpec("歌曲列表").setIndicator("歌曲列表",
                          res.getDrawable(R.drawable.album))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ArtistsActivity.class);
        spec = tabHost.newTabSpec("艺术家").setIndicator("艺术家",
                          res.getDrawable(R.drawable.artist))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, AlbumsActivity.class);
        spec = tabHost.newTabSpec("专辑").setIndicator("专辑",
                          res.getDrawable(R.drawable.album))
                      .setContent(intent);
        tabHost.addTab(spec);
    
        tabHost.setCurrentTab(1);
        
        gestureDetector = new GestureDetector(new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		};
    }
	//滑动切换
	class MyGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			TabHost tabHost = getTabHost();
			System.out.println("************");
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					//Log.i("test", "right");
					if (currentView == maxTabIndex) {
						currentView = 0;
					} else {
						currentView++;
					}
					tabHost.setCurrentTab(currentView);
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					//Log.i("test", "left");
					if (currentView == 0) {
						currentView = maxTabIndex;
					} else {
						currentView--;
					}
					tabHost.setCurrentTab(currentView);
				}
			} catch (Exception e) {
			}
			return false;
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
