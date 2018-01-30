package com.example.student.babydiary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by user on 2018/1/24.
 */

public class Grow_DataDAO {
    Context context;
    SQLiteDatabase db;
    public Grow_DataDAO(Context context)
    {
        this.context = context;
        MyDBHelper myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();
    }

    //新增成長資料
    public boolean addgrow(Grow_Data s)
    {
        ContentValues cv = new ContentValues();

        //輸入資料庫的資料不要號碼
        cv.put("date",s.date);
        cv.put("time",s.time);
        cv.put("tall",s.tall);
        cv.put("weight",s.weight);
        cv.put("headlength",s.headlength);
        cv.put("addtype",s.addtype);
        db.insert("badydata",null,cv);
        db.close();
        return true;
    }
    //查詢growDB 資料
    //讀出資料庫用不同的class Grow_DataOutout 因為欄位不一樣
    public ArrayList<Grow_DataOutput> getList() {
        ArrayList<Grow_DataOutput> mylist = new ArrayList<>();
        Cursor c = db.query("badydata", new String[] {"_id", "date","time", "tall","weight","headlength","addtype"}, null, null, null, null, null);
        if (c.moveToFirst())
        {
            Grow_DataOutput s1 = new Grow_DataOutput(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5),c.getInt(6));

            mylist.add(s1);
            while(c.moveToNext())
            {
                Grow_DataOutput s = new Grow_DataOutput(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5),c.getInt(6));
                mylist.add(s);
            }
        }
        return mylist;
    }

    //找出一筆gorw的資料
    public Grow_DataOutput getgrow(int grownum)
    {
        Cursor c = db.query("grow_data",new String[] {"_id", "date","time", "tall","weight","headlength","addtype"},"_id=?",
                new String[] {String.valueOf(grownum)},null,null,null);
        if (c.moveToFirst())
        {
            Grow_DataOutput s = new Grow_DataOutput(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5),c.getInt(6));
            return s;
        }
        return null;
    }


    //修改成長資料

    public boolean altergrow(Grow_DataOutput s)
    {
        ContentValues cv = new ContentValues();
        cv.put("date",s.date);
        cv.put("time",s.time);
        cv.put("tall",s.tall);
        cv.put("weight",s.weight);
        cv.put("headlength",s.headlength);

        db.update("badydata",cv,"_id=?",
                new String[]{String.valueOf(s.id)});
        return true;
    }


    //刪除成長資料
    public boolean deletegrow(int  grownum)
    {
        db.delete("grow_data","_grownum=?",new String[]{String.valueOf(grownum)});
        return true;
    }




}
