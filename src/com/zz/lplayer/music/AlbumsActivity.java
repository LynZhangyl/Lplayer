package com.zz.lplayer.music;

import com.zz.lplayer.music.R;
import com.zz.lplayer.adapter.AlbumsAdapter;
import com.zz.lplayer.util.AlbumList;
import com.zz.lplayer.util.MusicList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AlbumsActivity extends Activity {

	private ListView albumListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Log.v("test","AlbumActivity OnCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.albums);
		
		albumListView=(ListView) this.findViewById(R.id.albumListView);
		AlbumsAdapter adapter=new AlbumsAdapter(this, AlbumList.getAlbumData(this));
		albumListView.setAdapter(adapter);
		albumListView.setOnItemClickListener(new OnItemClickListener() {

			@Override//´ýÖØÐ´
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(AlbumsActivity.this,
//						MusicActivity.class);
//				intent.putExtra("id", arg2);
//				startActivity(intent);
				TextView tView=(TextView)arg1.findViewById(R.id.album_item_name);
				String album = tView.getText().toString();
				//Log.v("test","AlbumsActivity"+album);
				Intent intent = new Intent(AlbumsActivity.this,AlbumMusicActivity.class);
				intent.putExtra("album", album);
				startActivity(intent);
			}
		});
	}
}
