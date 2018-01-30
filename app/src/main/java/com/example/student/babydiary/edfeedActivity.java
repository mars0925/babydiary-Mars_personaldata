package com.example.student.babydiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.babydiary.data.Feed_DataOutout;

public class edfeedActivity extends AppCompatActivity {
    EditText edmk,edformu,edwean;
    int id;
    Feed_DataOutout s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edfeed);
        edmk = (EditText)findViewById(R.id.edfeed_mkmilk);
        edwean = (EditText)findViewById(R.id.edfeed_edweaning);
        edformu = (EditText)findViewById(R.id.feed_edformula);
        Intent it = getIntent();
        id = it.getIntExtra("id",0);

    }

    @Override
    //自動帶入點選的數字
    protected void onResume() {
        super.onResume();
        s = Main7Activity.dao.getfeed(id);
        edmk.setText(String.valueOf(s.mothermilk));
        edformu.setText(String.valueOf(s.formula));
        edwean.setText(String.valueOf(s.weaning));
    }



    //按下修改feed資料
    public void clickalter(View v)
    {
        Feed_DataOutout s1 = new Feed_DataOutout(id , s.date,s.time,Double.valueOf(edmk.getText().toString()),
                Double.valueOf(edformu.getText().toString()),Double.valueOf(edwean.getText().toString()),s.addtype);

        Main7Activity.dao.alterfeed(s1);
        finish();
    }



    //按下刪除feed資料
    public void clickdelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(edfeedActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("確認刪除此筆資料嗎?");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Main7Activity.dao.deletefeed(id);
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
