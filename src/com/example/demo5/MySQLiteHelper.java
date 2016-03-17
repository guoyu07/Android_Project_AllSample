package com.example.demo5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	public MySQLiteHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists hero_info("+ "id integer primary key," + "name varchar,"+ "level integer)");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}