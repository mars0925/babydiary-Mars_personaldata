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

import com.example.student.babydiary.data.Feed_DataOutout;
import com.example.student.babydiary.data.Personal_Data;
import com.example.student.babydiary.data.Personal_DataOutput;

import java.util.Calendar;

public class edpersonalActivity extends AppCompatActivity {
    Spinner spinner;
    EditText edname,edbirth;
    Personal_DataOutput s;
    int id ,edgender;
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edpersonal);
        spinner = (Spinner)findViewById(R.id.edgender);
        edname = (EditText)findViewById(R.id.edname);
        edbirth = (EditText)findViewById(R.id.edbirthday);
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
    }

    //按下修改person資料
    public void clickalter(View v)
    {
        Personal_DataOutput s1 = new Personal_DataOutput(id , edname.getText().toString(),edgender,edbirth.getText().toString());
        MainActivity.dao.alterpersonal(s1);
        finish();
    }
}
