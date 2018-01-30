package com.example.student.babydiary.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by user on 2018/1/24.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "badydaily.sqlite";
    final static int VERSION = 1;
    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE `personal_data` ( `_personalid` INTEGER PRIMARY KEY AUTOINCREMENT , `name` TEXT, `gender` INTEGER, `birthday` TEXT  )");
        //sqLiteDatabase.execSQL("CREATE TABLE `feed_data` ( `_feednum` INTEGER PRIMARY KEY AUTOINCREMENT, `date` TEXT,`time` TEXT, `mothermilk` INTEGER, `formula` INTEGER, `weaning` INTEGER )");
        //sqLiteDatabase.execSQL("CREATE TABLE `grow_data` ( `_grownum` INTEGER PRIMARY KEY AUTOINCREMENT, `date` TEXT,`time` TEXT, `tall` INTEGER, `weight` INTEGER, `headlength` INTEGER )");
        sqLiteDatabase.execSQL("CREATE TABLE `badydata` ( `_id` INTEGER PRIMARY KEY AUTOINCREMENT, `date` TEXT,`time` TEXT,`mothermilk` NUMERIC, `formula` NUMERIC, `weaning` NUMERIC, " +
                "`tall` NUMERIC, `weight` NUMERIC, `headlength` NUMERIC," +
                "`startsleep` TEXT,`endsleep` TEXT, `sleepday` TEXT,`sleephour` TEXT,`sleepmin` TEXT,`addtype` INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
