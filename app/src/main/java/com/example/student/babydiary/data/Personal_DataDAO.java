package com.example.student.babydiary.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 2018/1/24.
 */

public class Personal_DataDAO {
    Context context;
    SQLiteDatabase db;


    public Personal_DataDAO(Context context)
    {
        this.context = context;
        MyDBHelper myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();
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

    //修改寶寶資料
    public boolean alterbabydata(Personal_DataOutput  s)
    {
        ContentValues cv = new ContentValues();
        cv.put("name",s.name);
        cv.put("gender",s.gender);
        cv.put("birthday",s.birthday);

        db.update("personal_data",cv,"_personalid=?",
                new String[]{String.valueOf(s.personalid)});
        return true;
    }

    //刪除寶寶資料
    public boolean deletebaby(int  personalid)
    {
        db.delete("personal_data","_personalid=?",new String[]{String.valueOf(personalid)});
        return true;
    }
}
