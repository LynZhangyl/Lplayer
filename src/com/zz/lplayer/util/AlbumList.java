package com.zz.lplayer.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.R.bool;
import android.content.Context;
import android.util.Log;

import com.zz.lplayer.domain.Album;
import com.zz.lplayer.domain.Artist;
import com.zz.lplayer.domain.Music;

public class AlbumList {
	
	//获得专辑信息
	public static List<Album> getAlbumData(Context context){
		
		List<Album> albumsList = new ArrayList<Album>();
		List<Music> musicList = new ArrayList<Music>();
		
		musicList = MusicList.getMusicData(context);//首先获得歌曲列表
		Iterator<Music> iterator = musicList.iterator();//迭代器
		
		while(iterator.hasNext()){
			Music m = iterator.next();//得到每一首歌
			
			String albumname = m.getAlbum();
			Log.i("singer",albumname);
			boolean find = false;
			Iterator<Album> iterator2 = albumsList.iterator();
			
			while(iterator2.hasNext()){
				Album album = iterator2.next();
				String name = album.getAlbumName();
				Log.i("singer",name+" 对比");
				if(name.equals(albumname))
				{
					Log.i("singer", "相等");
					find =true;
					album.addMusic(m);
					album.setAccount(album.getAccount()+1);
					break;
				}
			}
			if(!find)
			{
				Log.i("singer", albumname+"添加");
				Album newAlbum = new Album();
				newAlbum.setAlbumName(albumname);
				newAlbum.setAccount(1);
				newAlbum.addMusic(m);
				albumsList.add(newAlbum);
			}
		}
		return albumsList;
	}

}
