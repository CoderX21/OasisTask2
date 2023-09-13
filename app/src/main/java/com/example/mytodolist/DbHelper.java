package com.example.mytodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private final static String DB_NAME="ganesh.db";
    private final static int VERSION=1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("INFO","database is created");
        db.execSQL("create table ganesh_table (id integer primary key autoincrement,name text not null,email text not null,password text not  null)");
        Log.d("INFO","table is created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("INFO","Database is upgraded, old version:"+oldVersion+",New version:"+newVersion);

    }
}
