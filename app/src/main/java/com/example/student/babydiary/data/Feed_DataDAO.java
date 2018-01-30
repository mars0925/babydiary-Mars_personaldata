package com.example.student.babydiary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by user on 2018/1/24.
 */

public class Feed_DataDAO {
    Context context;
    SQLiteDatabase db;
    public Feed_DataDAO(Context context)
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
    //查詢feedDB 資料
    //讀出資料庫用不同的class Feed_DataOutout 因為欄位不一樣
    public ArrayList<Feed_DataOutout> getList() {
        ArrayList<Feed_DataOutout> mylist = new ArrayList<>();
        Cursor c = db.query("badydata", new String[] {"_id", "date","time", "mothermilk","formula","weaning","addtype"}, null, null, null, null, null);
        if (c.moveToFirst())
        {
            Feed_DataOutout s1 = new Feed_DataOutout(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5),c.getInt(6));

            mylist.add(s1);
            while(c.moveToNext())
            {
                Feed_DataOutout s = new Feed_DataOutout(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getDouble(4),c.getDouble(5),c.getInt(6));
                mylist.add(s);
            }
        }
        return mylist;
    }

    //找出一筆feed的資料
    public Feed_DataOutout getfeed(int feednum)
    {
        Cursor c = db.query("badydata",new String[] {"_id", "date","time", "mothermilk","formula","weaning","addtype"},"_id=?",
                new String[] {String.valueOf(feednum)},null,null,null);
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
    public boolean deletefeed(int  feednum)
    {
        db.delete("badydata","_id=?",new String[]{String.valueOf(feednum)});
        return true;
    }




}
