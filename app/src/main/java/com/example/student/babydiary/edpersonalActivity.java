package com.example.student.babydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.student.babydiary.data.Feed_DataOutout;
import com.example.student.babydiary.data.Personal_Data;
import com.example.student.babydiary.data.Personal_DataOutput;

public class edpersonalActivity extends AppCompatActivity {
    Spinner spinner;
    EditText edname,edbirth;
    Personal_DataOutput s;
    int id ,edgender;
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
                    case 1:
                        edgender = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
