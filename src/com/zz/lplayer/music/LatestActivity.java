package com.zz.lplayer.music;


import java.util.List;

import com.zz.lplayer.adapter.MusicAdapter;

import com.zz.lplayer.dao.latestdao;
import com.zz.lplayer.domain.Music;
import com.zz.lplayer.music.R;
import com.zz.lplayer.util.MusicList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class LatestActivity extends Activity {

	private static int currentID;//当前歌曲ID
	private ListView listView;
	//private static TextView tvCurrentMusic;
	public static TextView tvCurrentMusic;
	private List<Music> listMusic;
	private Button btnDetail;
	public static String music;
	private String id;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("test","LatestActivity OnCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.songs);
		
		tvCurrentMusic = (TextView) findViewById(R.id.tvCurrentMusicName);	
		tvCurrentMusic.setText(music);
		
		btnDetail = (Button) findViewById(R.id.btnDetail);
		listView= (ListView) this.findViewById(R.id.listViewsongs);
		
		listMusic=MusicList.getLatestMusicData(getApplicationContext());//改
		//listMusic=MusicList.getMusicData(getApplicationContext());//
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
				SongsActivity.music = music;
				SongsActivity.tvCurrentMusic.setText(music);
				//ArtistMusicActivity.tvCurrentMusic.setText(music);
				//AlbumMusicActivity.tvCurrentMusic.setText(music);
				
				id = MusicList.getIDbyName(getApplicationContext(),music);
				url = MusicList.getURLbyName(getApplicationContext(), music);
				latestdao.insertData(url, music);
				Intent intent = new Intent(LatestActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});
		
		btnDetail.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				tvCurrentMusic.setText(music);
				SongsActivity.music = music;
				SongsActivity.tvCurrentMusic.setText(music);
				
				id = MusicList.getIDbyName(getApplicationContext(),music);
				url = MusicList.getURLbyName(getApplicationContext(), music);
				latestdao.insertData(url, music);
				//ArtistMusicActivity.tvCurrentMusic.setText(music);
				//AlbumMusicActivity.tvCurrentMusic.setText(music);
				Intent intent = new Intent(LatestActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});		
	}
	public void onResume(){
		//Log.v(TAG, "OnResume register Progress Receiver");
		super.onResume();										
		listView= (ListView) this.findViewById(R.id.listViewsongs);
		
		listMusic=MusicList.getLatestMusicData(getApplicationContext());//改
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
				SongsActivity.music = music;
				SongsActivity.tvCurrentMusic.setText(music);
				//ArtistMusicActivity.tvCurrentMusic.setText(music);
				//AlbumMusicActivity.tvCurrentMusic.setText(music);
				
				id = MusicList.getIDbyName(getApplicationContext(),music);
				url = MusicList.getURLbyName(getApplicationContext(), music);
				latestdao.insertData(url, music);
				Intent intent = new Intent(LatestActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});
		tvCurrentMusic = (TextView) findViewById(R.id.tvCurrentMusicName);	
		btnDetail = (Button) findViewById(R.id.btnDetail);
		btnDetail.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				tvCurrentMusic.setText(music);
				SongsActivity.music = music;
				SongsActivity.tvCurrentMusic.setText(music);
				id = MusicList.getIDbyName(getApplicationContext(),music);
				url = MusicList.getURLbyName(getApplicationContext(), music);
				//ArtistMusicActivity.tvCurrentMusic.setText(music);
				//AlbumMusicActivity.tvCurrentMusic.setText(music);
				Intent intent = new Intent(LatestActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});		
	}
}
