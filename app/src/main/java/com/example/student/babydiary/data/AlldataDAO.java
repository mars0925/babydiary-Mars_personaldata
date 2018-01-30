package com.example.student.babydiary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by user on 2018/1/27.
 */

public class AlldataDAO {
    Context context;
    SQLiteDatabase db;
    public AlldataDAO(Context context)
    {
        this.context = context;
        MyDBHelper myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();
    }

    //新增飲食資料
    public boolean addfeed(Feed_Data s)
    {
        ContentValues cv = new ContentValues();

        //cv.put("_feednum",s.feednum);//輸入資料庫的資料不要號碼
        cv.put("date",s.date);
        cv.put("time",s.time);
        cv.put("mothermilk",s.mothermilk);
        cv.put("formula",s.formula);
        cv.put("weaning",s.weaning);
        cv.put("addtype",s.addtype);
        db.insert("badydata",null,cv);
        db.close();
        return true;
    }


    public ArrayList<Outputdata> getList() {
        ArrayList<Outputdata> mylist = new ArrayList<>();
        Cursor c = db.query("badydata", new String[] {"_id", "date","time", "mothermilk","formula","weaning","tall","weight","headlength","startsleep","endsleep","sleepday","sleephour","sleepmin","addtype"}, null, null, null, null, null);
        if (c.moveToFirst())
        {
            Outputdata s1 = new Outputdata(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5),c.getDouble(6),c.getDouble(7),c.getDouble(8),c.getString(9),c.getString(10),c.getString(11),c.getString(12),c.getString(13),c.getInt(14));

            mylist.add(s1);
            while(c.moveToNext())
            {
                Outputdata  s =  new Outputdata(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5),c.getDouble(6),c.getDouble(7),c.getDouble(8),c.getString(9),c.getString(10),c.getString(11),c.getString(12),c.getString(13),c.getInt(14));
                mylist.add(s);
            }
        }
        return mylist;
    }

    //找出一筆feed的資料
    public Feed_DataOutout getfeed(int id)
    {
        Cursor c = db.query("badydata",new String[] {"_id", "date","time", "mothermilk","formula","weaning","addtype"},"_id=?",
                new String[] {String.valueOf(id)},null,null,null);
        if (c.moveToFirst())
        {
            Feed_DataOutout s = new Feed_DataOutout(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5),c.getInt(6));
            return s;
        }
        return null;
    }

    //修改飲食資料

    public boolean alterfeed(Feed_DataOutout s)
    {
        ContentValues cv = new ContentValues();
        cv.put("date",s.date);
        cv.put("time",s.time);
        cv.put("mothermilk",s.mothermilk);
        cv.put("formula",s.formula);
        cv.put("weaning",s.weaning);

        db.update("badydata",cv,"_id=?",
                new String[]{String.valueOf(s.feednum)});
        return true;
    }

    //刪除飲食資料
    public boolean deletefeed(int  id)
    {
        db.delete("badydata","_id=?",new String[]{String.valueOf(id)});
        return true;
    }


    //找出一筆gorw的資料
    public Grow_DataOutput getgrow(int id)
    {
        Cursor c = db.query("badydata",new String[] {"_id", "date","time", "tall","weight","headlength","addtype"},"_id=?",
                new String[] {String.valueOf(id)},null,null,null);
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
    public boolean deletegrow(int  id)
    {
        db.delete("badydata","_id=?",new String[]{String.valueOf(id)});
        return true;
    }


    //新增睡覺資料
    public boolean addsleep(Sleep_Data s)
    {
        ContentValues cv = new ContentValues();

        //cv.put("_feednum",s.feednum);//輸入資料庫的資料不要號碼
        cv.put("date",s.date);
        cv.put("time",s.time);
        cv.put("startsleep",s.startsleep);
        cv.put("endsleep",s.endsleep);
        cv.put("sleepday",s.sleepday);
        cv.put("sleephour",s.sleephour);
        cv.put("sleepmin",s.sleepmin);
        cv.put("addtype",s.addtype);
        db.insert("badydata",null,cv);
        db.close();
        return true;
    }


    //找出一筆sleep的資料
    public Sleep_DataOutput getsleep(int id)
    {
        Cursor c = db.query("badydata",new String[] {"_id", "date","time", "startsleep","endsleep","sleepday","sleephour","sleepmin","addtype"},"_id=?",
                new String[] {String.valueOf(id)},null,null,null);
        if (c.moveToFirst())
        {
            Sleep_DataOutput s = new Sleep_DataOutput(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getInt(8));
            return s;
        }
        return null;
    }

    //修改sleep資料

    public boolean altersleep(Sleep_DataOutput s)
    {
        ContentValues cv = new ContentValues();
        cv.put("date",s.date);
        cv.put("time",s.time);
        cv.put("startsleep",s.startsleep);
        cv.put("endsleep",s.endsleep);
        cv.put("sleepday",s.sleepday);
        cv.put("sleephour",s.sleephour);
        cv.put("sleepmin",s.sleepmin);

        db.update("badydata",cv,"_id=?",
                new String[]{String.valueOf(s.sleepnum)});
        return true;
    }

    //刪除sleep資料
    public boolean deletesleep(int  id)
    {
        db.delete("badydata","_id=?",new String[]{String.valueOf(id)});
        return true;
    }




    //新增寶寶資料
    public boolean addbaby(Personal_Data s)
    {
        ContentValues cv = new ContentValues();


        cv.put("name",s.name);
        cv.put("gender",s.gender);
        cv.put("birthday",s.birthday);
        db.insert("personal_data",null,cv);
        db.close();
        return true;
    }

    //找出一筆寶寶的資料
    public Personal_DataOutput getpersonaldata(int id) {
        Cursor c = db.query("personal_data", new String[]{"_personalid", "name", "gender", "birthday"}, "_personalid=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (c.moveToFirst()) {
            Personal_DataOutput s = new Personal_DataOutput(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3));
            return s;
        }
        return null;
    }

    //修改寶寶資料

    public boolean alterpersonal(Personal_DataOutput s)
    {
        ContentValues cv = new ContentValues();
        cv.put("name",s.name);
        cv.put("gender",s.gender);
        cv.put("birthday",s.birthday);


        db.update("personal_data",cv,"_personalid=?",
                new String[]{String.valueOf(s.personalid)});
        return true;
    }



}
