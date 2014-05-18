package com.zz.lplayer.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.widget.ListView;

public class Album {
	
	 private String albumName;
	 private int account;
	 private List<Music> musicList = new ArrayList<Music>();
	 
	 public String getAlbumName(){
		 return albumName;
	 }
	 
	 public void setAlbumName(String albumString){
		 this.albumName = albumString;
	 }
	 
	 public int getAccount() {
		 return account;	
	 }
	 
	 public void setAccount(int account){
		 this.account = account;
	 }
	 
	 public void addMusic(Music music)
	 {
		 musicList.add(music);
	 }
	 
	 public void removeMusic(Music music){
		 Iterator<Music> iterator = musicList.iterator();
		 while(iterator.hasNext()){
			 Music m = iterator.next();
			 if (m.equals(music)){
				 iterator.remove();
			 }
		 }
	 }
}
