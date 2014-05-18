package com.zz.lplayer.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class dbOpenHelper extends SQLiteOpenHelper {
	public static final int  VERSION=1;
	public static final String DBNAME="music.db" ;
	private static SQLiteDatabase dbOpenHelper1;
	
	public dbOpenHelper(Context context ){
		super (context,DBNAME,null,VERSION);
		//dbOpenHelper1.execSQL("create table latestlist(mid INTEGER primary key autoincrement,mpath TEXT,mname TEXT);");
		//onCreate(dbOpenHelper1);
		Log.v("test","dbOpenHelper construct");
	}
    public void onCreate(SQLiteDatabase db){
    	
    	db.execSQL("create table latestlist(mname TEXT primary key ,mpath TEXT);");
    	Log.v("test","dbOpenHelper onCreate");
    	//db.execSQL("create table t_music(mid INTEGER primary key autoincrement,mtitle vchar(20),msinger vchar(10),malbum vchar(20));");
    }
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newversion){
    	
    }
}
