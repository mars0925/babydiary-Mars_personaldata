package com.example.student.babydiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.babydiary.data.Sleep_DataOutput;

public class edsleepActivity extends AppCompatActivity {
    EditText edsleephour,edsleepmin;
    Sleep_DataOutput s;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edsleep);
        Intent it = getIntent();
        id = it.getIntExtra("id",0);
        edsleephour = (EditText)findViewById(R.id.sleephour);
        edsleepmin = (EditText)findViewById(R.id.sleepmin);

    }

    public void clickdelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(edsleepActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("確認刪除此筆資料嗎?");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Main7Activity.dao.deletesleep(id);
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

    //按下修改sleep資料
    public void clickalter(View v)
    {
        Sleep_DataOutput s1 = new Sleep_DataOutput(id , s.date,s.time,s.startsleep,
                s.endsleep,s.sleepday,edsleephour.getText().toString(),edsleepmin.getText().toString(),s.addtype);

        Main7Activity.dao.altersleep(s1);
        finish();
    }



    @Override
    //自動帶入點選的數字
    protected void onResume() {
        super.onResume();
        //從Main7Activity把資料拿過來
        s = Main7Activity.dao.getsleep(id);
        edsleephour.setText(s.sleephour);
        edsleepmin.setText(s.sleepmin);

    }

}
