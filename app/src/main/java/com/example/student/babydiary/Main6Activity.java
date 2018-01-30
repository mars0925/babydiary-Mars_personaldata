package com.example.student.babydiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main6Activity extends AppCompatActivity {
    Spinner sp,sp2,sp3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        sp= (Spinner)findViewById(R.id.spinner_support1);
        sp2= (Spinner)findViewById(R.id.spinner_support2);
        sp3= (Spinner)findViewById(R.id.spinner_support3);
        final String[] city = {"台北市","新北市","基隆市","宜蘭縣","桃園市","新竹縣","新竹市","苗栗縣","花蓮縣","台東縣"
                ,"台中市","彰化縣","南投縣","雲林縣","嘉義縣","嘉義市","台南市","高雄市","屏東縣","澎湖縣","金門","馬祖"
        };
        ArrayAdapter cityList = new ArrayAdapter<>(Main6Activity.this,
                android.R.layout.simple_spinner_dropdown_item,
                city);
        sp.setAdapter(cityList);
        sp2.setAdapter(cityList);
        sp3.setAdapter(cityList);
    }
}
