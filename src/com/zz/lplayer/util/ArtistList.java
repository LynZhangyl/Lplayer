package com.zz.lplayer.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.R.bool;
import android.content.Context;
import android.util.Log;

import com.zz.lplayer.domain.Artist;
import com.zz.lplayer.domain.Music;

public class ArtistList {
	
	//获得艺术家信息
	public static List<Artist> getArtistData(Context context){
		
		List<Artist> artistList = new ArrayList<Artist>();
		List<Music> musicList = new ArrayList<Music>();
		
		musicList = MusicList.getMusicData(context);//首先获得歌曲列表
		Iterator<Music> iterator = musicList.iterator();//迭代器
		
		while(iterator.hasNext()){
			Music m = iterator.next();//得到每一首歌
			
			String artistname = m.getSinger();
			Log.i("singer",artistname);
			boolean find = false;
			Iterator<Artist> iterator2 = artistList.iterator();
			
			while(iterator2.hasNext()){
				Artist artist = iterator2.next();
				String name = artist.getSingerName();
				Log.i("singer",name+" 对比");
				if(name.equals(artistname))
				{
					Log.i("singer", "相等");
					find =true;
					artist.addMusic(m);
					artist.setAccount(artist.getAccount()+1);
					break;
				}
			}
			if(!find)
			{
				Log.i("singer", artistname+"添加");
				Artist newArtist = new Artist();
				newArtist.setSingerName(artistname);
				newArtist.setAccount(1);
				newArtist.addMusic(m);
				artistList.add(newArtist);
			}
		}
		return artistList;
	}

}
