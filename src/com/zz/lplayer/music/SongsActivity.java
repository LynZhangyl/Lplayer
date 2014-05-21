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
import android.widget.ImageButton;
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
	static ImageButton btnStartStop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("test","SongsActivity OnCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.songs);
		tvCurrentMusic = (TextView) findViewById(R.id.tvCurrentMusicName);	
		tvCurrentMusic.setText(music);
		btnDetail = (Button) findViewById(R.id.btnDetail);
		btnStartStop = (ImageButton)findViewById(R.id.btnplaypause);
	
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
				SongsActivity.btnStartStop.setImageResource(R.drawable.pause1);
				LatestActivity.btnStartStop.setImageResource(R.drawable.pause1);
				Intent intent = new Intent(SongsActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});
		
		btnDetail.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				if(music==null)
					music = listMusic.get(0).getName();
				tvCurrentMusic.setText(music);
				LatestActivity.music = music;
				LatestActivity.tvCurrentMusic.setText(music);
				//AlbumMusicActivity.music = music;
				//ArtistMusicActivity.music = music;
				url = MusicList.getURLbyName(getApplicationContext(), music);
				id = MusicList.getIDbyName(getApplicationContext(), music);
				latestdao.insertData(url, music);
				
				SongsActivity.btnStartStop.setImageResource(R.drawable.pause1);
				LatestActivity.btnStartStop.setImageResource(R.drawable.pause1);
				Intent intent = new Intent(SongsActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});		
		
		btnStartStop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(music!=null)
				{
					if (MusicActivity.isPlaying == true) {

						Intent intent = new Intent(SongsActivity.this,
								MusicService.class);
						intent.putExtra("play", "pause");
						startService(intent);
						MusicActivity.isPlaying = false;
						MusicActivity.imageBtnPlay
								.setImageResource(R.drawable.play1);
						MusicActivity.replaying = false;
						btnStartStop.setImageResource(R.drawable.play1);
						SongsActivity.btnStartStop.setImageResource(R.drawable.play1);
						LatestActivity.btnStartStop.setImageResource(R.drawable.play1);
						
					} else {

						Intent intent = new Intent(SongsActivity.this,
								MusicService.class);
						intent.putExtra("play", "playing");
						intent.putExtra("id", id);
						startService(intent);
						MusicActivity.isPlaying = true;
						MusicActivity.imageBtnPlay
								.setImageResource(R.drawable.pause1);
						MusicActivity.replaying = true;
						btnStartStop.setImageResource(R.drawable.pause1);
						SongsActivity.btnStartStop.setImageResource(R.drawable.pause1);
						LatestActivity.btnStartStop.setImageResource(R.drawable.pause1);					
					}
				}
			}
		});
	}

}
