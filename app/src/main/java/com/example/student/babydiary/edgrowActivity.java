package com.example.student.babydiary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.student.babydiary.data.Feed_DataOutout;
import com.example.student.babydiary.data.Grow_DataOutput;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class edgrowActivity extends AppCompatActivity {
    EditText edtall,edweight,edheadlength;
    Grow_DataOutput s;
    ImageView edimage;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edgrow);
        edtall = findViewById(R.id.edgrow_tall);
        edweight = findViewById(R.id.edgrow_weight);
        edheadlength = findViewById(R.id.edgrow_headlength);
        edimage = findViewById(R.id.edgrow_image);


        Intent it = getIntent();
        id = it.getIntExtra("id",0);
        s = Main7Activity.dao.getgrow(id);
        loadphoto(s);
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
        loadphoto(s);


    }



    //按下修改feed資料
    public void clickalter(View v)
    {
        Grow_DataOutput s1 = new Grow_DataOutput(id , s.date,s.time,Double.valueOf(edtall.getText().toString()),
                Double.valueOf(edweight.getText().toString()),Double.valueOf(edheadlength.getText().toString()),s.imagename,s.addtype);

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
    public void loadphoto(Grow_DataOutput s) {

        File f = new File(getExternalFilesDir("PHOTO"), s.imagename);
        try {
            InputStream is = new FileInputStream(f);
            Log.d("BMP", "Can READ:" + f);
            Bitmap bmp = getFitImage(is);
            edimage.setImageBitmap(bmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
