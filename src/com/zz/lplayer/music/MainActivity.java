package com.zz.lplayer.music;

import com.zz.lplayer.dao.latestdao;
import com.zz.lplayer.music.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;



public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("test","MainActivity OnCreate");
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);// 用于全屏显示
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setContentView(R.layout.main);
		latestdao latestdao1 = new latestdao(getApplicationContext());
		
		Resources res = getResources(); 
        TabHost tabHost = getTabHost(); 
        TabHost.TabSpec spec; 
        Intent intent;  
        
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

    }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
