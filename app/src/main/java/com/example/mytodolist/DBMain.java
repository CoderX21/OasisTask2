package com.example.mytodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMain extends SQLiteOpenHelper {
    private final static String DB_NAME="student";
    private final static String TABLE_NAME="course";
    private final static int VERSION=1;

    public DBMain(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TABLE_NAME+" (id integer primary key autoincrement,avatar blob,title text not null,description text not null)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String query="drop table if exists "+TABLE_NAME+"";
    db.execSQL(query);
    }
}
