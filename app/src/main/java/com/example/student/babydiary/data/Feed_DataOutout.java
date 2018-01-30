package com.example.student.babydiary.data;

/**
 * Created by user on 2018/1/24.
 */

public class Feed_DataOutout {
    public int feednum;
    public String date;//日期
    public String time;//時間
    public Double mothermilk;
    public Double formula;
    public Double weaning;
    public int addtype;
    public  Feed_DataOutout(int feednum ,String date,String time, Double mothermilk, Double formula, Double weaning,int addtype)
    {
        this.feednum = feednum;
        this.time = time;
        this.date = date;
        this.mothermilk = mothermilk;
        this.formula = formula;
        this.weaning = weaning;
        this.addtype = addtype;

    }
}
