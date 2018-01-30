package com.example.student.babydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
    public void click2(View v)
    {
        Intent it = new Intent(this,FeedActivity.class);
        startActivity(it);
    }
    public void click3(View v)
    {
        Intent it = new Intent(this,sleepActivity.class);
        startActivity(it);
    }
    public void click4(View v)
    {
        Intent it = new Intent(this,growActivity.class);
        startActivity(it);
    }
    public void click5(View v)
    {
        Intent it = new Intent(this,Main6Activity.class);
        startActivity(it);
    }
    public void click6(View v)
    {
        finish();
    }
    public void click7(View v)
    {
        Intent it = new Intent(this,Main7Activity.class);
        startActivity(it);
    }
}
