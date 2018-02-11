package com.example.student.babydiary.data;

/**
 * Created by Student on 2018/1/23.
 */

public class Grow_DataOutput {
    public int id;
    public String date;//日期
    public String time;//時間
    public double tall;
    public double weight;
    public double headlength;//頭圍
    public String imagename;
    public int addtype;
    public Grow_DataOutput(int id,String date, String time, double tall, double weight, double headlength,String imagename,int addtype)
    {
        this.id = id;
        this.date=date;
        this.time=time;
        this.tall=tall;
        this.weight=weight;
        this.headlength=headlength;
        this.imagename = imagename;
        this.addtype = addtype;

    }
}
