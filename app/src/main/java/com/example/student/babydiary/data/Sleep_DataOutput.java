package com.example.student.babydiary.data;

/**
 * Created by Student on 2018/1/23.
 */

public class Sleep_DataOutput {
    public int sleepnum;
    public String date;//日期
    public String time;//時間
    public String startsleep;
    public String endsleep;
    public String sleepday;
    public String sleephour;
    public String sleepmin;
    public int addtype;


    public Sleep_DataOutput(int sleepnum,String date, String time, String startsleep, String endsleep,  String sleepday, String sleephour, String sleepmin,int addtype)
    {

        this.sleepnum = sleepnum;
        this.time = time;
        this.date = date;
        this.startsleep = startsleep;
        this.endsleep = endsleep;
        this.sleepday = sleepday;
        this.sleephour = sleephour;
        this.sleepmin = sleepmin;
        this.addtype = addtype ;
    }

}
