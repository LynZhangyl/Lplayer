package com.zz.lplayer.adapter;

import java.util.List;

import com.zz.lplayer.music.R;
import com.zz.lplayer.domain.Artist;
import com.zz.lplayer.domain.Music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ArtistsAdapter extends BaseAdapter {
	
    private List<Artist> listArtist;
    private Context context;
    private TextView textArtistName;
    private TextView textMusicNum;
    
    public ArtistsAdapter(Context context,List<Artist> listArtist){
    	this.context=context;
    	this.listArtist=listArtist;
    }
	public void setListItem(List<Artist> listArtist){
		this.listArtist=listArtist;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listArtist.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listArtist.get(arg0);
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
			convertView=LayoutInflater.from(context).inflate(R.layout.artist_item, null);
		}
		Artist a=listArtist.get(position);
		//¸èÊÖÃû
		textArtistName=(TextView) convertView.findViewById(R.id.artist_item_name);
		textArtistName.setText(a.getSingerName());
		//¸èÇúÊý
		textMusicNum=(TextView) convertView.findViewById(R.id.artest_item_number);
		textMusicNum.setText(String.valueOf(a.getAccount()));
//		textArtistName.setOnClickListener(new OnClickListener() {
//            
//            @Override
//            publicvoid onClick(View v) {
//                                       
//                
//            }	
		return convertView;
	}
	
}
	
