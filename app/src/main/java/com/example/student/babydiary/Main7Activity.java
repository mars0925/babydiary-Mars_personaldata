package com.example.student.babydiary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
    TextView settime,setcontext,tv_age,tv_time;
    public static AlldataDAO dao;
    Date birthDay;
    /*声明日期及时间变量*/
    private int mYear;
    private int mMonth;
    private int mDay;
    /*声明对象变量*0/
    public TimePicker tp;
    public DatePicker dp;
    Calendar c,c1;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tv_age = findViewById(R.id.age);
        tv_time = (TextView) findViewById(R.id.textView_time);
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

        //監聽tv_time內容有沒有發生改變
        tv_time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //重新改設定新的日期
                date = tv_time.getText().toString();//用來查詢listview的日期
                //adapter更新
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /*
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
        */
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

        date = tv_time.getText().toString();//用來查詢listview的日期
    }
    //解出menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main7menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    //menu點擊的事件.
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
        date = tv_time.getText().toString();//用來查詢listview的日期
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //設定feed的顯示內容
    public String setfeedcontext(int i,String date)
    {
        String contextstr;
        contextstr = "母奶 " + dao.getListbydate(date).get(i).mothermilk + "CC" + "\n" +
                "配方奶 " + dao.getListbydate(date).get(i).formula +  "CC" + "\n" +
                "副食品 " + dao.getListbydate(date).get(i).weaning +  "CC";
        return contextstr;
    }


    //設定grow的顯示內容
    public String setgrowcontext(int i,String date)
    {
        String contextstr;
        contextstr = "身高 " + dao.getListbydate(date).get(i).tall + "公分" + "\n" +
                "體重 " + dao.getListbydate(date).get(i).weight +  "公斤" + "\n" +
                "頭圍 " + dao.getListbydate(date).get(i).headlength +  "公分";
        return contextstr;
    }

    //設定sleep的顯示內容
    public String setsleepcontext(int i,String date)
    {
        String hr =  dao.getListbydate(date).get(i).sleephour;
        String min =  dao.getListbydate(date).get(i).sleepmin;
        String contextstr;
        contextstr = "寶寶睡覺 " + dao.getListbydate(date).get(i).startsleep + "" + "\n" +
                "總共睡了 " + hr + "小時"+ min + "分鐘";
        //"寶寶起床 " + dao.getList().get(i).endsleep +  "" + "\n" +
        return contextstr;
    }

    //計算年紀
    public void computeage()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            //取得生日的date格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            birthDay = sdf.parse(dao.getpersonaldata(1).birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try
        {
            int  day_of_month = 30;
            //取得當下日期
            Date d1 = new Date();
            // getTime()回傳距離1970年1月1日的毫秒差.
            long diff = d1.getTime()-birthDay.getTime();
            //兩者差距
            long diffday = diff/(24*60*60*1000);
            //年差
            long  year = diffday/365;
            //月差距
            long  month = (long)((diffday-year*365)/day_of_month);
            //日差距
            long  day = (long)((diffday-year*365-month*day_of_month)%day_of_month);



            if (diffday < 0)
            {
                tv_age.setText("寶寶還沒出生");
            }
            else {
                if (year == 0 && month == 0)
                {
                    tv_age.setText("寶寶未滿1個月");
                }
                else
                {
                    if (year ==0)
                    {
                        tv_age.setText("寶寶已經" + month +"個月了");
                    }
                    else
                    {
                        tv_age.setText("寶寶已經" + year + "歲"+ month+"個月了");
                    }
                }


            }
        }
        catch (Exception e)
        {

        }




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
            return dao.getListbydate(date).size() ;
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

            settime.setText(String.valueOf(dao.getListbydate(date).get(i).time));
            if (dao.getListbydate(date).get(i).addtype == 1)
            {
                setcontext.setText(setfeedcontext(i,date));
            }
            else if (dao.getListbydate(date).get(i).addtype == 2)
            {
                setcontext.setText(setgrowcontext(i,date));
            }

            else if (dao.getListbydate(date).get(i).addtype == 3)
            {
                setcontext.setText(setsleepcontext(i,date));
            }


            return v;
        }
    }
}
