package com.zz.lplayer.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.widget.ListView;

public class Artist {
	
	 private String singer;
	 private int account;
	 private List<Music> musicList = new ArrayList<Music>();
	 
	 public String getSingerName(){
		 return singer;
	 }
	 
	 public void setSingerName(String singer){
		 this.singer = singer;
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
