package com.zz.lplayer.music;

import java.util.List;

import com.zz.lplayer.adapter.MusicAdapter;
import com.zz.lplayer.dao.latestdao;
import com.zz.lplayer.domain.Music;
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

public class AlbumMusicActivity extends Activity{

	private ListView listView;
	private static int currentID;//当前歌曲ID
	//private static TextView tvCurrentMusic;
	private List<Music> listMusic;
	private Button btnDetail;
	public static TextView tvCurrentMusic;
	public static String music;
	private String id;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("test","AlbumMusicActivity OnCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.songs);
		
		tvCurrentMusic = (TextView) findViewById(R.id.tvCurrentMusicName);	
		btnDetail = (Button) findViewById(R.id.btnDetail);
		
		Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();//获得参数
        String album = bundle.getString( "album");
        Log.v("test","ArtistMusicActivity"+album);
        
        music = SongsActivity.music;
		tvCurrentMusic.setText(music);
		
		listView= (ListView) this.findViewById(R.id.listViewsongs);
		listMusic=MusicList.getAlbumMusicData(getApplicationContext(),album);
		
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
				LatestActivity.tvCurrentMusic.setText(music);
				SongsActivity.tvCurrentMusic.setText(music);
				LatestActivity.music = music;
				SongsActivity.music = music;
				//ArtistMusicActivity.tvCurrentMusic.setText(music);
				
				id = MusicList.getIDbyName(getApplicationContext(),music);
				url = MusicList.getURLbyName(getApplicationContext(), music);
				latestdao.insertData(url, music);
				Intent intent = new Intent(AlbumMusicActivity.this,MusicActivity.class);
				currentID = Integer.parseInt(id);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});
	
		btnDetail.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				tvCurrentMusic.setText(music);
				LatestActivity.tvCurrentMusic.setText(music);
				SongsActivity.tvCurrentMusic.setText(music);
				LatestActivity.music = music;
				SongsActivity.music = music;
				//ArtistMusicActivity.tvCurrentMusic.setText(music);
				id = MusicList.getIDbyName(getApplicationContext(),music);
				url = MusicList.getURLbyName(getApplicationContext(), music);
				latestdao.insertData(url, music);
				currentID = Integer.parseInt(id);
				Intent intent = new Intent(AlbumMusicActivity.this,MusicActivity.class);
				intent.putExtra("id", currentID);
				startActivity(intent);
			}
		});	
	}

}
