package com.example.student.babydiary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.student.babydiary.data.AlldataDAO;
import com.example.student.babydiary.data.Grow_Data;
import com.example.student.babydiary.data.Grow_DataDAO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class growActivity extends AppCompatActivity {
    final public int imagerequest = 456;
    TextView tv6;
    /*声明日期及时间变量*/
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int msec;
    private ImageView im;
    String imagename;
    public Uri uri;

    /*声明对象变量*/
    TimePicker tp;
    DatePicker dp;
    TextView inputtall,inputweight,inputhead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow);
        inputtall = (TextView)findViewById(R.id.inputtall);
        inputweight = (TextView)findViewById(R.id.inputweight);
        inputhead = (TextView)findViewById(R.id.inputhead);
        im = (ImageView)findViewById(R.id.grow_image);

        /*取得目前日期与时间*/
        Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        mHour=c.get(Calendar.HOUR_OF_DAY);
        mMinute=c.get(Calendar.MINUTE);
        msec = c.get(Calendar.SECOND);
        tv6 = (TextView) findViewById(R.id.grow_showtime);
        updateDisplay();
        dp = new DatePicker(growActivity.this);
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
        tp = new TimePicker(growActivity.this);
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
        //命名照片
        imagename = new StringBuilder().append("i").append(mYear).append("_")
                .append(format(mMonth + 1)).append("_")
                .append(format(mDay)).append(format(mHour))
                .append(format(mMinute)).append(format(msec))
                .append(".jpg").toString();
        //點擊imageView後拍照放入
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //利用intent調用相機拍照
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File f = new File(getExternalFilesDir("PHOTO"), imagename);
                //Log.d("name", imagename);
                //Log.d("path", String.valueOf(Environment.getExternalStorageDirectory()));

                uri = FileProvider.getUriForFile(growActivity.this, "com.example.student.babydiary.provider", f);
                Log.d("pat111", String.valueOf(uri));
                it.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(it, imagerequest);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == imagerequest) {
            if (resultCode == RESULT_OK) {
                File f = new File(getExternalFilesDir("PHOTO"), imagename);
                try {
                    InputStream is = new FileInputStream(f);
                    Log.d("BMP", "Can READ:" + is.available());
                    Bitmap bmp = getFitImage(is);
                    im.setImageBitmap(bmp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //解出照片格式
    public static Bitmap getFitImage(InputStream is) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        byte[] bytes = new byte[0];
        try {
            bytes = readStream(is);
            //BitmapFactory.decodeStream(inputStream, null, options);
            Log.d("BMP", "byte length:" + bytes.length);
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            System.gc();
            // Log.d("BMP", "Size:" + bmp.getByteCount());
            return bmp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解出照片格式用的readStream方法
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    //讀取資料夾中照片的方法
    public void loadphoto() {
        File f = new File(getExternalFilesDir("PHOTO"), imagename);
        try {
            InputStream is = new FileInputStream(f);
            //Log.d("BMP", "Can READ:" + is.available());
            Bitmap bmp = getFitImage(is);
            im.setImageBitmap(bmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void clickadd(View v)
    {
    //日期和時間的格式
        String date;
        String time;
        date = new StringBuilder().append(mYear).append("/")
                .append(format(mMonth + 1)).append("/")
                .append(format(mDay)).toString();
        time = new StringBuilder().append(format(mHour)).append(":")
                .append(format(mMinute)).toString();

        Grow_Data grow_data = new Grow_Data(date,time ,Double.valueOf(inputtall.getText().toString()),
                Double.valueOf(inputweight.getText().toString()),Double.valueOf(inputhead.getText().toString()),imagename);
        Log.d("pat222222", String.valueOf(uri));
        AlldataDAO dao = new AlldataDAO(growActivity.this);
        dao.addgrow(grow_data);
        finish();
    }

    //清除填寫內容
    public void click_reset(View v)
    {
        inputtall.setText("0");
        inputweight.setText("0");
        inputhead.setText("0");
    }



}