package com.example.student.babydiary.data;

/**
 * Created by Student on 2018/1/23.
 */

public class Grow_Data {
    //public int grownum;//輸入資料庫的資料不要號碼
    public String date;//日期
    public String time;//時間
    public double tall;
    public double weight;
    public double headlength;//頭圍
    public int addtype;
    public Grow_Data(String data, String time, double tall, double weight, double headlength)
    {
        this.date=data;
        this.time=time;
        this.tall=tall;
        this.weight=weight;
        this.headlength=headlength;
        this.addtype = 2;

    }
}
