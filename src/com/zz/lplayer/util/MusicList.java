
package com.zz.lplayer.util;

import java.util.ArrayList;
import java.util.List;

import com.zz.lplayer.dao.latestdao;
import com.zz.lplayer.domain.Music;

import android.R.string;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;


public class MusicList {

	public static List<Music> getMusicData(Context context) {
		List<Music> musicList = new ArrayList<Music>();
		ContentResolver cr = context.getContentResolver();
		if (cr != null) {
			// 获取所有歌曲
			Cursor cursor = cr.query(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
					null, MediaStore.Audio.Media.ARTIST);//所得结果按歌手排序
			if (null == cursor) {
				return null;
			}
			if (cursor.moveToFirst()) {
				do {
					Music m = new Music();
					String title = cursor.getString(cursor
							.getColumnIndex(MediaStore.Audio.Media.TITLE));
					String singer = cursor.getString(cursor
							.getColumnIndex(MediaStore.Audio.Media.ARTIST));
					if ("<unknown>".equals(singer)) {
						singer = "未知艺术家";
					}
					String album = cursor.getString(cursor
							.getColumnIndex(MediaStore.Audio.Media.ALBUM));
					long size = cursor.getLong(cursor
							.getColumnIndex(MediaStore.Audio.Media.SIZE));
					long time = cursor.getLong(cursor
							.getColumnIndex(MediaStore.Audio.Media.DURATION));
					String url = cursor.getString(cursor
							.getColumnIndex(MediaStore.Audio.Media.DATA));
					String name = cursor
							.getString(cursor
									.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
					String sbr = name.substring(name.length() - 3,
							name.length());
					//Log.e("--------------", sbr);
					if (sbr.equals("mp3")) {
						m.setTitle(title);
						m.setSinger(singer);
						m.setAlbum(album);
						m.setSize(size);
						m.setTime(time);
						m.setUrl(url);
						m.setName(name);
						musicList.add(m);
					}
				} while (cursor.moveToNext());
			}
		}
		return musicList;
	}
	public static List<Music> getArtistMusicData(Context context,String artist) {
		Log.v("test","MusicList "+artist);
		List<Music> musicList = new ArrayList<Music>();
		ContentResolver cr = context.getContentResolver();
		if (cr != null) {
			// 获取所有歌曲
			Cursor cursor = cr.query(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
					null, MediaStore.Audio.Media.ARTIST);//所得结果按歌手排序
			if (null == cursor) {
				return null;
			}
			if (cursor.moveToFirst()) {
				do {
					Music m = new Music();
					
					String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
					if ("<unknown>".equals(singer)) {
						singer = "未知艺术家";
					}
					String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
					String sbr = name.substring(name.length() - 3, name.length());
					//Log.e("--------------", sbr);
					
					if (sbr.equals("mp3") && singer.equals(artist)) {
						Log.v("test","MusicList "+artist+ "  "+ singer);
						String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
						String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
						long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
						long time = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
						String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
						
						m.setTitle(title);
						m.setSinger(singer);
						m.setAlbum(album);
						m.setSize(size);
						m.setTime(time);
						m.setUrl(url);
						m.setName(name);
						musicList.add(m);
					}
				} while (cursor.moveToNext());
			}
		}
		return musicList;
	}
	public static List<Music> getAlbumMusicData(Context context,String albumS) {
		Log.v("test","MusicList"+albumS);
		List<Music> musicList = new ArrayList<Music>();
		
		ContentResolver cr = context.getContentResolver();
		if (cr != null) {
			// 获取所有歌曲
			Cursor cursor = cr.query(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
					null, MediaStore.Audio.Media.ARTIST);//所得结果按歌手排序
			if (null == cursor) {
				return null;
			}
			if (cursor.moveToFirst()) {
				do {
					Music m = new Music();
					
					String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
					
					String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
					String sbr = name.substring(name.length() - 3,name.length());
					//Log.e("--------------", sbr);
					
					if (sbr.equals("mp3") && album.equals(albumS)) {
						Log.v("test","Album & AlbumS"+album+ "  "+ albumS);
						String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
						String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
						if ("<unknown>".equals(singer)) {
							singer = "未知艺术家";
						}
						long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
						long time = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
						String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
						m.setTitle(title);
						m.setSinger(singer);
						m.setAlbum(album);
						m.setSize(size);
						m.setTime(time);
						m.setUrl(url);
						m.setName(name);
						musicList.add(m);
					}
				} while (cursor.moveToNext());
			}
		}
		return musicList;
	}
	public static List<Music> getLatestMusicData(Context context) {
		
		String []latestStrings = new String[10];
		latestStrings = latestdao.latestlist();//获得最近播放的歌曲的url
		Log.v("test", "MusicList StringSize " + latestStrings.length);
		List<Music> musicList = new ArrayList<Music>();
		ContentResolver cr = context.getContentResolver();
		if (cr != null) {
			// 获取所有歌曲
			Cursor cursor = cr.query(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
					null, MediaStore.Audio.Media.ARTIST);//所得结果按歌手排序
			if (null == cursor) {
				return null;
			}
			for(int i = 0;i<latestStrings.length;i++){
				if (cursor.moveToFirst()) {
					do {
						Music m = new Music();
						String url = cursor.getString(cursor
								.getColumnIndex(MediaStore.Audio.Media.DATA));

						String name = cursor
								.getString(cursor
										.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
						String sbr = name.substring(name.length() - 3,
								name.length());
						// Log.e("--------------", sbr);

						if (sbr.equals("mp3") && url.equals(latestStrings[i])) {

							String title = cursor
									.getString(cursor
											.getColumnIndex(MediaStore.Audio.Media.TITLE));
							String singer = cursor
									.getString(cursor
											.getColumnIndex(MediaStore.Audio.Media.ARTIST));
							if ("<unknown>".equals(singer)) {
								singer = "未知艺术家";
							}
							long size = cursor
									.getLong(cursor
											.getColumnIndex(MediaStore.Audio.Media.SIZE));
							long time = cursor
									.getLong(cursor
											.getColumnIndex(MediaStore.Audio.Media.DURATION));
							String album = cursor
									.getString(cursor
											.getColumnIndex(MediaStore.Audio.Media.ALBUM));
							// String url =
							// cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
							m.setTitle(title);
							m.setSinger(singer);
							m.setAlbum(album);
							m.setSize(size);
							m.setTime(time);
							m.setUrl(url);
							m.setName(name);
							musicList.add(m);
							break;
						}
					} while (cursor.moveToNext());
				} 
			}
		}
		return musicList;
	}
	
	public static String getIDbyName(Context context,String musicname) {
		List<Music> musicList  = getMusicData(context);
		int id;
		for( id = 0;id<musicList.size();id++ )
		{
			Music music = musicList.get(id);
			String nameString = music.getName();
			if(nameString.equals(musicname))
			{
				Log.v("test","MusicList " + id);
				return String.valueOf(id);
			}
		}
		return null;		
	}
	public static String getURLbyName(Context context,String musicname) {
		List<Music> musicList  = getMusicData(context);
		int id;
		for( id = 0;id<musicList.size();id++ )
		{
			Music music = musicList.get(id);
			String nameString = music.getName();
			if(nameString.equals(musicname))
			{
				Log.v("test","MusicList getURL " + music.getUrl());
				return music.getUrl();
			}
		}
		return null;		
	}
}
