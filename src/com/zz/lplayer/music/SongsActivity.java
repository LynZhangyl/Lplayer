package com.zz.lplayer.music;


import java.util.List;


import com.zz.lplayer.music.R;
import com.zz.lplayer.adapter.MusicAdapter;
import com.zz.lplayer.dao.latestdao;
import com.zz.lplayer.domain.Music;
import com.zz.lplayer.util.MusicList;

import android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SongsActivity extends Activity{
	
	private static int currentID;//µ±Ç°¸èÇúID
	private ListView listView;
	private List<Music> listMusic;
	private Button btnDetail;
	public static TextView tvCurrentMusic;
	
	public static  String music =null;
	private String id;
	private String url;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("test","SongsActivity OnCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.songs);
		tvCurrentMusic = (TextView) findViewById(R.id.tvCurrentMusicName);	
		tvCurrentMusic.setText(music);
		btnDetail = (Button) findViewById(R.id.btnDetail);
		listView= (ListView) this.findViewById(R.id.listViewsongs);
		listMusic=MusicList.getMusicData(getApplicationContext());//
		MusicAdapter adapter=new MusicAdapter(this, listMusic);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView tView=(TextView)arg1.findViewById(R.id.music_item_name);
				music = tView.getText().toString();
				tvCurrentMusic.setText(music);
				LatestActivity.music = music;
				LatestActivity.tvCurrentMusic.setText(music);
				//AlbumMusicActivity.music = music;
				//AlbumMusicActivity.tvCurrentMusic.setText(music);
				//ArtistMusicActivity.music = music;
				
				url = MusicList.getURLbyName(getApplicationContext(), music);
				id = MusicList.getIDbyName(getApplicationContext(), music);
				Log.v("test","SongsActivity getURL "+ url);
				latestdao.insertData(url, music);
				Intent intent = new Intent(SongsActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});
		
		btnDetail.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				tvCurrentMusic.setText(music);
				LatestActivity.music = music;
				LatestActivity.tvCurrentMusic.setText(music);
				//AlbumMusicActivity.music = music;
				//ArtistMusicActivity.music = music;
				url = MusicList.getURLbyName(getApplicationContext(), music);
				id = MusicList.getIDbyName(getApplicationContext(), music);
				latestdao.insertData(url, music);
				Intent intent = new Intent(SongsActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});		
	}

}
