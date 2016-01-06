package com.bilue.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	public MyOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql5 = "CREATE TABLE " + DatabaseConfig.tablename5 + " ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DatabaseConfig.sensorname + " VARCHAR(20),"
				+ DatabaseConfig.values + " VARCHAR(20)," + DatabaseConfig.time
				+ " VARCHAR(20) " + " )";
		String sql60 = "CREATE TABLE " + DatabaseConfig.tablename60 + " ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DatabaseConfig.sensorname + " VARCHAR(20),"
				+ DatabaseConfig.values + " VARCHAR(20)," + DatabaseConfig.time
				+ " VARCHAR(20) " + " )";
		db.execSQL(sql5);
		db.execSQL(sql60);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
