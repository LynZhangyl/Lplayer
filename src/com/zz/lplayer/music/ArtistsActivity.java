package com.zz.lplayer.music;

import com.zz.lplayer.music.R;
import com.zz.lplayer.adapter.ArtistsAdapter;
import com.zz.lplayer.util.ArtistList;
import com.zz.lplayer.util.MusicList;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ArtistsActivity extends Activity {
   
	public static final String TAG = "com.zz.lplayer.music.ArtistsActivity";
	private ListView artistListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Log.v("test","AritistActivity OnCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.artist);
		
		artistListView=(ListView) this.findViewById(R.id.artistListView);
		ArtistsAdapter adapter=new ArtistsAdapter(this, ArtistList.getArtistData(this));
		artistListView.setAdapter(adapter);
		artistListView.setOnItemClickListener(new OnItemClickListener() {

			@Override //
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView tView=(TextView)arg1.findViewById(R.id.artist_item_name);
				String artist = tView.getText().toString();
				//Log.v("test","ArtistsActivity "+artist);
				Intent intent = new Intent(ArtistsActivity.this,
						ArtistMusicActivity.class);
				intent.putExtra("artist", artist);
				startActivity(intent);
			}
		});
	}
}
