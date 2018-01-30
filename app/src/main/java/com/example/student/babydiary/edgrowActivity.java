package com.example.student.babydiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.babydiary.data.Feed_DataOutout;
import com.example.student.babydiary.data.Grow_DataOutput;

public class edgrowActivity extends AppCompatActivity {
    EditText edtall,edweight,edheadlength;
    Grow_DataOutput s;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edgrow);
        edtall = findViewById(R.id.edgrow_tall);
        edweight = findViewById(R.id.edgrow_weight);
        edheadlength = findViewById(R.id.edgrow_headlength);

        Intent it = getIntent();
        id = it.getIntExtra("id",0);

    }
    @Override
    //自動帶入點選的數字
    protected void onResume() {
        super.onResume();
        //從Main7Activity把資料拿過來
        s = Main7Activity.dao.getgrow(id);
        edtall.setText(String.valueOf(s.tall));
        edweight.setText(String.valueOf(s.weight));
        edheadlength.setText(String.valueOf(s.headlength));
    }



    //按下修改feed資料
    public void clickalter(View v)
    {
        Grow_DataOutput s1 = new Grow_DataOutput(id , s.date,s.time,Double.valueOf(edtall.getText().toString()),
                Double.valueOf(edweight.getText().toString()),Double.valueOf(edheadlength.getText().toString()),s.addtype);

        Main7Activity.dao.altergrow(s1);
        finish();
    }



    //按下刪除grow資料
    public void clickdelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(edgrowActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("確認刪除此筆資料嗎?");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Main7Activity.dao.deletegrow(id);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }
}
