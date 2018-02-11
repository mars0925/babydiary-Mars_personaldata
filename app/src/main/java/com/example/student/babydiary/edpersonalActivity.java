package com.example.student.babydiary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.student.babydiary.data.Feed_DataOutout;
import com.example.student.babydiary.data.Personal_Data;
import com.example.student.babydiary.data.Personal_DataOutput;

import java.util.Calendar;

public class edpersonalActivity extends AppCompatActivity {
    Spinner sp_city, spinner;
    EditText edname,edbirth;
    Personal_DataOutput s;
    int id ,edgender,cityname;
    final String[] city = {"台北市","新北市","基隆市","宜蘭縣","桃園市","新竹縣","新竹市","苗栗縣","花蓮縣","台東縣"
            ,"台中市","彰化縣","南投縣","雲林縣","嘉義縣","嘉義市","台南市","高雄市","屏東縣","澎湖縣","金門縣","連江縣"
    };


    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edpersonal);
        spinner = (Spinner)findViewById(R.id.edgender);
        edname = (EditText)findViewById(R.id.edname);
        edbirth = (EditText)findViewById(R.id.edbirthday);
        sp_city = (Spinner)findViewById(R.id.spinner_edcity);
        Intent it = getIntent();
        id = it.getIntExtra("id",0);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(edpersonalActivity.this,
                R.array.gender,android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinner.getSelectedItemPosition())
                {
                    case 0:
                        edgender = 1;
                        break;
                    case 1:
                        edgender = 0;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter cityList = new ArrayAdapter<>(edpersonalActivity.this,
                R.layout.myspinner,city
        );
        cityList.setDropDownViewResource(R.layout.myspinner);

        sp_city.setAdapter(cityList);
        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position)
                {
                    case 0: cityname = 0; break;
                    case 1: cityname = 1; break;
                    case 2: cityname = 2; break;
                    case 3: cityname = 3; break;
                    case 4: cityname = 4; break;
                    case 5: cityname = 5; break;
                    case 6: cityname = 6; break;
                    case 7: cityname = 7; break;
                    case 8: cityname = 8; break;
                    case 9: cityname = 9; break;
                    case 10: cityname = 10; break;
                    case 11: cityname = 11; break;
                    case 12: cityname = 12; break;
                    case 13: cityname = 13; break;
                    case 14: cityname = 14; break;
                    case 15: cityname = 15; break;
                    case 16: cityname = 16; break;
                    case 17: cityname = 17; break;
                    case 18: cityname = 18; break;
                    case 19: cityname = 19; break;
                    case 20: cityname = 20; break;
                    case 21: cityname = 21; break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        edbirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    c=Calendar.getInstance();
                    new DatePickerDialog(edpersonalActivity.this,android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {

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
                            edbirth.setText(year + "/" + strmon + "/" + strday);
                        }
                    }, c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH)).show();
                }
            }
        });


        edbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = Calendar.getInstance();
                new DatePickerDialog(edpersonalActivity.this,android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {

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
                        edbirth.setText(year + "/" + strmon + "/" + strday);
                    }

                }, c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH)).show();

            }
        });

    }



    @Override
    //自動帶入點選的數字
    protected void onResume() {
        super.onResume();
        s = MainActivity.dao.getpersonaldata(id);
        Log.d("cityname",String.valueOf(s.cityname));
        edname.setText(String.valueOf(s.name));
        if (s.gender ==1)
        {
            spinner.setSelection(0);//設定預設值
        }
        else if (s.gender == 0)
        {
            spinner.setSelection(1);
        }
        edbirth.setText(String.valueOf(s.birthday));

        switch (s.cityname)
        {
            case 0:
                sp_city.setSelection(0);
                break;
            case 1:
                sp_city.setSelection(1);
                break;
            case 2:
                sp_city.setSelection(2);
                break;
            case 3:
                sp_city.setSelection(3);
                break;
            case 4:
                sp_city.setSelection(4);
                break;
            case 5:
                sp_city.setSelection(5);
                break;
            case 6:
                sp_city.setSelection(6);
                break;
            case 7:
                sp_city.setSelection(7);
                break;
            case 8:
                sp_city.setSelection(8);
                break;
            case 9:
                sp_city.setSelection(9);
                break;
            case 10:
                sp_city.setSelection(10);
                break;
            case 11:
                sp_city.setSelection(11);
                break;
            case 12:
                sp_city.setSelection(12);
                break;
            case 13:
                sp_city.setSelection(13);
                break;
            case 14:
                sp_city.setSelection(14);
                break;
            case 15:
                sp_city.setSelection(15);
                break;
            case 16:
                sp_city.setSelection(16);
                break;
            case 17:
                sp_city.setSelection(17);
                break;
            case 18:
                sp_city.setSelection(18);
                break;
            case 19:
                sp_city.setSelection(19);
                break;
            case 20:
                sp_city.setSelection(20);
                break;
            case 21:
                sp_city.setSelection(21);
                break;

        }

    }

    //按下修改person資料
    public void clickalter(View v)
    {
        Personal_DataOutput s1 = new Personal_DataOutput(id , edname.getText().toString(),edgender,edbirth.getText().toString(),cityname);
        MainActivity.dao.alterpersonal(s1);
        finish();
    }
}
