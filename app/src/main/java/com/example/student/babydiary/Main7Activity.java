package com.example.student.babydiary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.student.babydiary.data.AlldataDAO;
import com.example.student.babydiary.data.Feed_DataDAO;
import com.example.student.babydiary.data.Grow_DataDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main7Activity extends AppCompatActivity {
    ListView listView;
    Myadapter adapter;
    TextView settime,setcontext,tv_age;
    EditText tv_time;
    public static AlldataDAO dao;
    Date birthDay;
    /*声明日期及时间变量*/
    private int mYear;
    private int mMonth;
    private int mDay;
    /*声明对象变量*/
    public TimePicker tp;
    public DatePicker dp;
    Calendar c,c1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        tv_age = findViewById(R.id.age);
        tv_time = findViewById(R.id.editText_time);

        listView = (ListView)findViewById(R.id.listView);
        //設定dao看資料是哪一張表
        dao = new AlldataDAO(Main7Activity.this);
        adapter = new Myadapter();
        listView.setAdapter(adapter);
        //讓listview item可以被點選,然後跳到修改的那一頁
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (dao.getList().get(i).addtype == 1)
                {
                    Intent it = new Intent(Main7Activity.this,edfeedActivity.class);
                    //用putExtra把資料送出到edfeedactivity
                    it.putExtra("id",dao.getList().get(i).id);
                    startActivity(it);
                }
                else if (dao.getList().get(i).addtype == 2)
                {
                    Intent it = new Intent(Main7Activity.this,edgrowActivity.class);
                    it.putExtra("id",dao.getList().get(i).id);
                    startActivity(it);
                }
                else if (dao.getList().get(i).addtype == 3)
                {
                    Intent it = new Intent(Main7Activity.this,edsleepActivity.class);
                    it.putExtra("id",dao.getList().get(i).id);
                    startActivity(it);
                }


            }
        });

        //第一次安裝時,如果寶寶資料庫沒有東西就轉跳先填資料
        try {
           if (dao.getpersonaldata(1).name == "")
           {           }
        }
        catch (Exception e)
        {
                Intent it  = new Intent(Main7Activity.this,MainActivity.class);
                startActivity(it);
        }

        computeage();//計算年紀
        tv_time.setText(getdateformat());//設定今天的日期
        //用日曆來選擇日期
        tv_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    c1 = Calendar.getInstance();
                    new DatePickerDialog(Main7Activity.this,android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            String strmon,strday;

                            if(dayOfMonth <10){
                                strday="0"+dayOfMonth;
                            }else{
                                strday =String.valueOf(dayOfMonth);
                            }

                            if((monthOfYear+1) <10){
                                strmon="0"+(monthOfYear+1);
                            }else{
                                strmon =String.valueOf(monthOfYear+1);
                            }
                            tv_time.setText(year + "/" + strmon + "/" + strday);
                        }
                    }, c1.get(c1.YEAR), c1.get(c1.MONTH), c1.get(c1.DAY_OF_MONTH)).show();
                }
            }
        });
        //用日曆來選擇日期
        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c1 = Calendar.getInstance();
                new DatePickerDialog(Main7Activity.this,android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int  year, int monthOfYear, int dayOfMonth) {
                        String strmon,strday;

                        if(dayOfMonth <10){
                            strday="0"+dayOfMonth;
                        }else{
                            strday =String.valueOf(dayOfMonth);
                        }

                        if((monthOfYear+1) <10){
                            strmon="0"+(monthOfYear+1);
                        }else{
                            strmon =String.valueOf(monthOfYear+1);
                        }
                        tv_time.setText(year + "/" + strmon + "/" + strday);
                    }

                }, c1.get(c1.YEAR), c1.get(c1.MONTH), c1.get(c1.DAY_OF_MONTH)).show();

            }
        });


    }
    //解出menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main7menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    //menu點擊的事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_feed:
                Intent it1 = new Intent(this,FeedActivity.class);
                startActivity(it1);
                break;
            case R.id.menu_grow:
                Intent it2 = new Intent(this,growActivity.class);
                startActivity(it2);
                break;
            case R.id.menu_sleep:
                Intent it3 = new Intent(this,sleepActivity.class);
                startActivity(it3);
                break;
            case R.id.menu_supply:
                Intent it4 = new Intent(this,Main6Activity.class);
                startActivity(it4);
                break;
            case R.id.menu_persaonal:
                Intent it5 = new Intent(this,MainActivity.class);
                startActivity(it5);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //此頁重新載入
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        computeage();//計算年紀
        tv_time.setText(getdateformat());
    }

    //設定feed的顯示內容
    public String setfeedcontext(int i)
    {
        String contextstr;
        contextstr = "母奶 " + dao.getList().get(i).mothermilk + "CC" + "\n" +
                "配方奶 " + dao.getList().get(i).formula +  "CC" + "\n" +
                "副食品 " + dao.getList().get(i).weaning +  "CC";
        return contextstr;
    }


    //設定grow的顯示內容
    public String setgrowcontext(int i)
    {
        String contextstr;
        contextstr = "身高 " + dao.getList().get(i).tall + "公分" + "\n" +
                "體重 " + dao.getList().get(i).weight +  "公斤" + "\n" +
                "頭圍 " + dao.getList().get(i).headlength +  "公分";
        return contextstr;
    }

    //設定sleep的顯示內容
    public String setsleepcontext(int i)
    {
        String hr =  dao.getList().get(i).sleephour;
        String min =  dao.getList().get(i).sleepmin;
        /*
        String timestr = dao.getList().get(i).sleeptime; //取睡覺的時間
        String[] str = timestr.split(":");//剖析分鐘的部分
        int hr = Integer.valueOf(str[1]);//轉整數去掉前面的0
        int m = Integer.valueOf(str[2]);//轉整數去掉前面的0
        */

        String contextstr;
        contextstr = "寶寶睡覺 " + dao.getList().get(i).startsleep + "" + "\n" +
                "總共睡了 " + hr + "小時"+ min + "分鐘";
        //"寶寶起床 " + dao.getList().get(i).endsleep +  "" + "\n" +
        return contextstr;
    }

    //計算年紀
    public void computeage()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            birthDay = sdf.parse(dao.getpersonaldata(1).birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH)+1;


        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH)+1;

        int birthsum = yearBirth*12+monthBirth;
        int nowsum = yearNow*12+monthNow;
        int difmonth = nowsum-birthsum;
        int year = difmonth/12;
        int month = difmonth%12;
        tv_age.setText("寶寶已經" + year + "年"+ month+"月了");


    }

    private void updateDisplay() {
        tv_time.setText(
                new StringBuilder().append(mYear).append("/")
                        .append(format(mMonth + 1)).append("/")
                        .append(format(mDay)).append("　"));
    }
    /*日期时间显示两位数的方法*/
    private String format(int x)
    {
        String s=""+x;
        if(s.length()==1) s="0"+s;
        return s;
    }

    //整理目前日期的格式  2018/02/01
    public String getdateformat()
    {
        c = Calendar.getInstance();
        String strday,strmon;
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        if(day <10){
            strday = "0" + day;
        }else{
            strday = String.valueOf(day);
        }

        if((month+1) <10){
            strmon="0"+(month+1);
        }else{
            strmon =String.valueOf(month+1);
        }
        return (year + "/" + strmon + "/" + strday);

    }



    //自訂baseadapter 給listview用
    class Myadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return dao.getList().size() ;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(Main7Activity.this);
            //解出自訂的layout
            View v = inflater.inflate(R.layout.diarylayout,null);
            //找到自訂layout上面的textview

            settime = v.findViewById(R.id.settime);
            setcontext = v.findViewById(R.id.setcontext);

            settime.setText(String.valueOf(dao.getList().get(i).time));
            if (dao.getList().get(i).addtype == 1)
            {
                setcontext.setText(setfeedcontext(i));
            }
            else if (dao.getList().get(i).addtype == 2)
            {
                setcontext.setText(setgrowcontext(i));
            }

            else if (dao.getList().get(i).addtype == 3)
            {
                setcontext.setText(setsleepcontext(i));
            }


            return v;
        }
    }
}
