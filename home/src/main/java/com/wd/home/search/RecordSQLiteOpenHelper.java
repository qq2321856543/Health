package com.wd.home.search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {
    private static String name = "record.db";
    private static Integer version = 1;


    public RecordSQLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //打开数据库，建立了一个叫records的表，里面只有一列name来存储历史记录：
        sqLiteDatabase.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
    }
}
