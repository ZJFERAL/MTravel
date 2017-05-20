package com.mcy.mtravel.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by machengyuan on 2017/5/3.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "mt.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_SEARCH_HISTORY = "table_search";


    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "
                + TABLE_SEARCH_HISTORY
                + "(_id integer primary key autoincrement,keyword,time)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists " + TABLE_SEARCH_HISTORY);
            onCreate(db);
        }
    }

}
