package com.example.student.babydiary;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.student.babydiary.data.AlldataDAO;
import com.example.student.babydiary.data.Feed_Data;
import com.example.student.babydiary.data.Sleep_Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class sleepActivity extends AppCompatActivity {
    TextView tv6;

    private TextView timer;
    private Button start;
    private Button stop;
    private Button zero;
    private Button end;
    private boolean startflag=false;
    private int tsec=0,csec=0,cmin=0,chr = 0;


    /*声明日期及时间变量*/
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private long   ut1;
    public String date;
    public String time;
    /*聲明声明对象变量*/
    TimePicker tp;
    DatePicker dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        timer = (TextView)findViewById(R.id.timer);
        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        zero = (Button)findViewById(R.id.zero);
        end = (Button)findViewById(R.id.end);





        //宣告Timer
        Timer timer01 =new Timer();
        //設定Timer(task為執行內容，0代表立刻開始,間格1秒執行一次)
        timer01.schedule(task, 0,1000);
        //Button監聽
        start.setOnClickListener(listener);
        stop.setOnClickListener(listener);
        zero.setOnClickListener(listener);
        end.setOnClickListener(listener);





        /*取得目前日期与时间*/
        Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        mHour=c.get(Calendar.HOUR_OF_DAY);
        mMinute=c.get(Calendar.MINUTE);
        tv6 = (TextView) findViewById(R.id.sleep_showtime);
        updateDisplay();
        dp = new DatePicker(sleepActivity.this);
        dp.init(mYear,mMonth,mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int  year, int monthOfYear, int dayOfMonth) {
                mYear=year;
                mMonth= monthOfYear;
                mDay=dayOfMonth;
                /*调用updateDisplay()来改变显示日期*/
                updateDisplay();
            }
        });
         /*取得TimePicker对象，并设置为24小时制显示*/
        tp = new TimePicker(sleepActivity.this);
        tp.setIs24HourView(true);
        /*setOnTimeChangedListener，并重写onTimeChanged event*/
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                mHour=hourOfDay;
                mMinute=minute;
                /*调用updateDisplay()来改变显示时间*/
                updateDisplay();
            }
        });

        //日期和時間的格式 存到資料庫用

        date = new StringBuilder().append(mYear).append("/")
                .append(format(mMonth + 1)).append("/")
                .append(format(mDay)).toString();
        time = new StringBuilder().append(format(mHour)).append(":")
                .append(format(mMinute)).toString();


    }
    /*设置显示日期时间的方法*/
    private void updateDisplay() {
        tv6.setText(
                new StringBuilder().append(mYear).append("/")
                        .append(format(mMonth + 1)).append("/")
                        .append(format(mDay)).append("　")
                        .append(format(mHour)).append(":")
                        .append(format(mMinute)));
    }

    /*日期时间显示两位数的方法*/
    private String format(int x)
    {
        String s=""+x;
        if(s.length()==1) s="0"+s;
        return s;
    }



    private Handler handler = new Handler(){
        public  void  handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    String msec,mmin, mhr,mdays,timecount;
                    long days = tsec / ( 60 * 60 * 24);
                    long hours = (tsec-days*( 60 * 60 * 24))/( 60 * 60);
                    long minutes = (tsec-days*( 60 * 60 * 24)-hours*( 60 * 60))/(60);
                    long sec = (tsec-days*( 60 * 60 * 24)-hours*( 60 * 60))%(60);

                    //秒的格式
                    if(sec < 10){
                        msec = "0"+sec;
                    }
                    else
                    {
                        msec = String.valueOf(sec);
                    }
                    //分的格式
                    if(minutes <10){
                        mmin="0"+minutes;
                    }else{
                        mmin=""+minutes;
                    }
                    //小時的格式
                    if(hours <10){
                        mhr="0"+hours;
                    }else{
                        mhr=""+hours;
                    }
                    //天的格式
                    if(days <10){
                        mdays="0"+days;
                    }else{
                        mdays=""+days;
                    }

                    timecount = mdays+":" + mhr+":" + mmin+":"+msec;
                    //s字串為00:00格式
                    timer.setText(timecount);
                    break;
            }
        }
    };


    private TimerTask task = new TimerTask(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (startflag){
            //如果startflag是true則每秒tsec+1
                tsec++;
                Message message = new Message();
                //傳送訊息1
                message.what =1;
                handler.sendMessage(message);
            }
        }
    };

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.start:
                    startflag=true;
                    break;
                case R.id.stop:
                    startflag=false;
                    break;
                case R.id.zero:
                    tsec=0;
                //TextView 初始化
                    timer.setText("00:00");
                    break;
                case R.id.end:
                    finish();
                    break;
            }
        }
    };

    public void clicksave(View v)
    {


        //按下儲存的時候在取一次時間
        Calendar c2 =Calendar.getInstance();
        mYear=c2.get(Calendar.YEAR);
        mMonth=c2.get(Calendar.MONTH);
        mDay=c2.get(Calendar.DAY_OF_MONTH);
        mHour=c2.get(Calendar.HOUR_OF_DAY);
        mMinute=c2.get(Calendar.MINUTE);
        String endtime = new StringBuilder().append(format(mHour)).append(":")
                .append(format(mMinute)).toString();
        String  startsleeep = time;
        String  endsleep = endtime;

        String str1 = timer.getText().toString();

        String[] str = str1.split(":");//剖析分鐘的部分

        String sleepday = str[0];
        String sleephour = str[1];
        String sleepmin = str[2];


        Sleep_Data sleep_data = new Sleep_Data(date,time ,startsleeep,
                endsleep,sleepday,sleephour,sleepmin);

        AlldataDAO dao = new AlldataDAO(sleepActivity.this);
        dao.addsleep(sleep_data);

    }

}