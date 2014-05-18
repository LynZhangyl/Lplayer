package com.zz.lplayer.dao;

import java.util.ResourceBundle.Control;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;

public class latestdao  {
	private static  dbOpenHelper helper;
	private static SQLiteDatabase db;
	public  latestdao(Context context) {
		helper=new dbOpenHelper(context);
		
		Log.v("test","latestdao Construct");
	}
	
	// LatestList.insertData(fullPath);在每一个点击进行播放的listener里添加这句进行最近播放列表的插入操作
	
	public static void insertData(String fullPath,String name) { 
		Log.v("test","latestdao URL " +fullPath);
		
		db=helper.getWritableDatabase();
        // 先判断数据库中是否有这条数据  
        String sql1 = "select mname from latestlist where mpath = ? "; 
        String sql2="delete from latestlist where mpath='"+fullPath+"'"; 
        String sql3="insert into latestlist(mname,mpath) values('" + name+"','"+fullPath+"')";
       //判断是否存在 存在则删除之前已存在的 且插入新的
       // String string;
       Cursor cursor =db.rawQuery(sql1, new String[] {String.valueOf(fullPath)} );
       int num=cursor.getCount();
       Log.v("test","latestdao insert get num "+ num);
       if (num!=0){
    	  //找到了删除后再添加
    	  db.execSQL(sql2);
    	  Log.v("test","latestdao exec sql2 " +sql2);
    	  db.execSQL(sql3);  
      }
      else{
    	  //找不到直接insert 这个fullpath
    	  Log.v("test","exec "+ sql3);
    	  db.execSQL(sql3);
    	  
      }
      
    }  
	 
	//每次调用这个函数给出表中二十行数据的url 
	public static String[] latestlist (  ){
		//返回查表的url的集合
		db=helper.getWritableDatabase();
		String[] string=new String[10];
	// String sql1 = "select mpath from latestlist "; 
		String[] columns ={"mpath"};
	 Cursor cursor =db.query("latestlist",columns , null, null, null, null, null);
	 int num=cursor.getCount();
	 Log.v("test" , "latestdao getString[] num "+ num);
	 if(num>10)
	     num = 10;
	 int i=0;
	 cursor.moveToLast();
	 while(num>0)
	 {
		 string[i++] = cursor.getString(cursor.getColumnIndex("mpath"));
		 cursor.moveToPrevious();
		 num--;
	 }
	 

	  return string;
	}
	
	//每次调用这个函数清空数据库的最近播放列表
	
}
