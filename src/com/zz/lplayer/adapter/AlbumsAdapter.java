package com.zz.lplayer.adapter;

import java.util.List;


import com.zz.lplayer.domain.Album;
import com.zz.lplayer.domain.Music;
import com.zz.lplayer.music.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AlbumsAdapter extends BaseAdapter {
	
    private List<Album> listAlbum;
    private Context context;
    public AlbumsAdapter(Context context,List<Album> listAlbum){
    	this.context=context;
    	this.listAlbum=listAlbum;
    }
	public void setListItem(List<Album> listAlbum){
		this.listAlbum=listAlbum;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listAlbum.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listAlbum.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.album_item, null);
		}
		Album a=listAlbum.get(position);
		//专辑名
		TextView textAlbumName=(TextView) convertView.findViewById(R.id.album_item_name);
		textAlbumName.setText(a.getAlbumName());
		//歌曲数目
		TextView textMusicNum=(TextView) convertView.findViewById(R.id.album_item_number);
		textMusicNum.setText(String.valueOf(a.getAccount()));
	
		return convertView;
	}
	
}
