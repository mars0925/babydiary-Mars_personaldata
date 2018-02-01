package com.example.student.babydiary;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.student.babydiary.data.AlldataDAO;
import com.example.student.babydiary.data.Feed_Data;
import com.example.student.babydiary.data.Feed_DataDAO;
import com.example.student.babydiary.data.Feed_DataOutout;
import com.example.student.babydiary.data.Grow_Data;
import com.example.student.babydiary.data.Grow_DataDAO;
import com.example.student.babydiary.data.Grow_DataOutput;
import com.example.student.babydiary.data.MyDBHelper;
import com.example.student.babydiary.data.Personal_Data;
import com.example.student.babydiary.data.Personal_DataDAO;
import com.example.student.babydiary.data.Personal_DataOutput;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity{
    TextView tv,tv2,tv3,tv4;
    EditText ed,ed2,ed3;
    Spinner sp;
    Calendar c;
    public static AlldataDAO dao;
    RadioGroup radioGroup_sex;
    int gender;
    ArrayList<String> personaldata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personaldata = new ArrayList<>();
        dao = new AlldataDAO(MainActivity.this);
        //宣告radioGrop
        radioGroup_sex = (RadioGroup)findViewById(R.id.radioGroup_sex);
        ed3 = (EditText)findViewById(R.id.edittext_city);
        ed = (EditText)findViewById(R.id.editText_name);
        tv = new TextView(this);
        tv2 = new TextView(this);
        tv3 = new TextView(this);
        tv4 = new TextView(this);
        ed2 = (EditText) findViewById(R.id.editText_Birthday);
        ed2.setInputType(InputType.TYPE_NULL);
        ed2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    c=Calendar.getInstance();
                    new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {

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
                            ed2.setText(year + "/" + strmon + "/" + strday);
                        }
                    }, c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH)).show();
                }
            }
        });


        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = Calendar.getInstance();
                new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {

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
                        ed2.setText(year + "/" + strmon + "/" + strday);
                    }

                }, c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH)).show();

            }
        });


        //radiobutton確認選項
        radioGroup_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup radioGroup, int checkedId ) {
                   switch (checkedId)
                   {
                       case R.id.radioButton_boy:
                           gender = 1;
                            break;
                       case R.id.radioButton_girl:
                           gender = 0;
                           break;
                   }
               }
           });
        reload();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_personal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_save:
                save();
                break;
            case R.id.menu_alter:
                alter();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();

    }

    //按確定會把寶寶資料存到DB
    public void save()
    {
        //String str = String.valueOf(ed.getText());
        //Log.d("MSFFFFF",str);


        if (ed.getText().toString().matches(""))
        {
            AlertDialog.Builder ad;
            ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("確認資料");//設定視窗標題
            ad.setMessage("姓名不可以是空白");//設定顯示的文字
            ad.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.show();
        }

        else if ( ed2.getText().toString().matches(""))
        {
            AlertDialog.Builder ad;
            ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("確認資料");//設定視窗標題
            ad.setMessage("生日不可以是空白");//設定顯示的文字
            ad.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.show();
        }
        else if ( ed3.getText().toString().matches(""))
        {
            AlertDialog.Builder ad;
            ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("確認資料");//設定視窗標題
            ad.setMessage("戶籍地不可以是空白");//設定顯示的文字
            ad.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.show();
        }

        else {

            Personal_DataDAO dao = new Personal_DataDAO(MainActivity.this);
            //姓名,性別代號,生日


            Personal_Data personal_data = new Personal_Data(ed.getText().toString(),gender,ed2.getText().toString());
            //加入資料庫
            dao.addbaby(personal_data);
            finish();
        }


    }

    //修改
    public void alter()
    {
        Intent it  = new Intent(MainActivity.this,edpersonalActivity.class);
        it.putExtra("id",1);
        startActivity(it);
    }

    public void reload()
    {
        try {
            //資料庫有資料時代入資料
            ed.setText(dao.getpersonaldata(1).name);
            ed2.setText(dao.getpersonaldata(1).birthday);
            if (dao.getpersonaldata(1).gender == 1) {
                radioGroup_sex.check(R.id.radioButton_boy);
            } else if (dao.getpersonaldata(1).gender == 0) {
                radioGroup_sex.check(R.id.radioButton_girl);
            }
        }
        catch (Exception e)
        {

        }
    }

}
