package com.example.student.babydiary;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.babydiary.data.AlldataDAO;
import com.example.student.babydiary.data.Personal_Data;
import com.example.student.babydiary.data.Personal_DataDAO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity{
    TextView tv,tv2,tv3,tv4;
    EditText ed,ed2,ed3;
    Spinner sp_city;
    Calendar c;
    public static AlldataDAO dao;
    RadioGroup radioGroup_sex;
    int gender;
    final public int imagerequest = 456;
    ArrayList<String> personaldata;
    public ImageView im;
    final String[] city = {"台北市","新北市","基隆市","宜蘭縣","桃園市","新竹縣","新竹市","苗栗縣","花蓮縣","台東縣"
            ,"台中市","彰化縣","南投縣","雲林縣","嘉義縣","嘉義市","台南市","高雄市","屏東縣","澎湖縣","金門縣","連江縣"
    };
    public int cityname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personaldata = new ArrayList<>();
        dao = new AlldataDAO(MainActivity.this);
        //宣告radioGrop
        radioGroup_sex = (RadioGroup)findViewById(R.id.radioGroup_sex);
        ed = (EditText)findViewById(R.id.editText_name);
        im = (ImageView)findViewById(R.id.badyphto);
        sp_city = (Spinner)findViewById(R.id.spinner_city);
        tv = new TextView(this);
        tv2 = new TextView(this);
        tv3 = new TextView(this);
        tv4 = new TextView(this);
        ed2 = (EditText) findViewById(R.id.editText_Birthday);
        ed2.setInputType(InputType.TYPE_NULL);
        ed2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    c=Calendar.getInstance();
                    new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {

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
                            ed2.setText(year + "/" + strmon + "/" + strday);
                        }
                    }, c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH)).show();
                }
            }
        });

        ArrayAdapter cityList = new ArrayAdapter<>(MainActivity.this,
                R.layout.myspinner,city
        );
        cityList.setDropDownViewResource(R.layout.myspinner);

        sp_city.setAdapter(cityList);
        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (sp_city.getSelectedItemPosition())
                {
                    case 0:
                    cityname = 0;
                    break;
                    case 1:
                    cityname = 1;
                    break;
                    case 2:
                    cityname = 2;
                    break;
                    case 3:
                    cityname = 3;
                    break;
                    case 4:
                    cityname = 4;
                    break;
                    case 5:
                    cityname = 5;
                    break;
                    case 6:
                    cityname = 6;
                    break;
                    case 7:
                    cityname = 7;
                    break;
                    case 8:
                    cityname = 8;
                    break;
                    case 9:
                    cityname = 9;
                    break;
                    case 10:
                    cityname = 10;
                    break;
                    case 11:
                    cityname = 11;
                    break;
                    case 12:
                    cityname = 12;
                    break;
                    case 13:
                    cityname = 13;
                    break;
                    case 14:
                    cityname = 14;
                    break;
                    case 15:
                    cityname = 15;
                    break;
                    case 16:
                    cityname = 16;
                    break;
                    case 17:
                    cityname = 17;
                    break;
                    case 18:
                    cityname = 18;
                    break;
                    case 19:
                    cityname = 19;
                    break;
                    case 20:
                    cityname = 20;
                    break;
                    case 21:
                    cityname = 21;
                    break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = Calendar.getInstance();
                new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {

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
                        ed2.setText(year + "/" + strmon + "/" + strday);
                    }

                }, c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH)).show();

            }
        });


        //radiobutton確認選項
        radioGroup_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup radioGroup, int checkedId ) {
                   switch (checkedId)
                   {
                       case R.id.radioButton_boy:
                           gender = 1;
                            break;
                       case R.id.radioButton_girl:
                           gender = 0;
                           break;
                   }
               }
           });

        //點擊imageView後拍照放入
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File f = new File(getExternalFilesDir("PHOTO"), "myphoto.jpg");


                Uri uri = FileProvider.getUriForFile(MainActivity.this, "com.example.student.babydiary.provider", f);
                it.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                startActivityForResult(it, imagerequest);
            }
        });

        reload();
    }


    //接受immageview點擊後送出的resultCode
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //方法的返回的intent的extras中存储在对应data下，一张缩略图
        if (requestCode == imagerequest)
        {
            if (resultCode == RESULT_OK)
            {
                File f = new File(getExternalFilesDir("PHOTO"), "myphoto.jpg");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_personal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_save:
                save();
                break;
            case R.id.menu_alter:
                alter();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
        loadphoto();

    }



    //按確定會把寶寶資料存到DB
    public void save()
    {
        //String str = String.valueOf(ed.getText());
        //Log.d("MSFFFFF",str);


        if (ed.getText().toString().matches(""))
        {
            AlertDialog.Builder ad;
            ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("確認資料");//設定視窗標題
            ad.setMessage("姓名不可以是空白");//設定顯示的文字
            ad.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.show();
        }

        else if ( ed2.getText().toString().matches(""))
        {
            AlertDialog.Builder ad;
            ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("確認資料");//設定視窗標題
            ad.setMessage("生日不可以是空白");//設定顯示的文字
            ad.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.show();
        }
        /*
        else if ( ed3.getText().toString().matches(""))
        {
            AlertDialog.Builder ad;
            ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("確認資料");//設定視窗標題
            ad.setMessage("戶籍地不可以是空白");//設定顯示的文字
            ad.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.show();
        }*/

        else {


            //姓名,性別代號,生日
            Personal_Data personal_data = new Personal_Data(ed.getText().toString(),gender,ed2.getText().toString(),cityname);
            Log.d("cityname",String.valueOf(cityname));
            //加入資料庫
            dao.addbaby(personal_data);

            finish();
        }


    }

    //修改
    public void alter()
    {
        Intent it  = new Intent(MainActivity.this,edpersonalActivity.class);
        it.putExtra("id",1);
        startActivity(it);
    }

    //資料庫有資料時代入資料
    public void reload()
    {
        try {
            //資料庫有資料時代入資料
            ed.setText(dao.getpersonaldata(1).name);
            ed2.setText(dao.getpersonaldata(1).birthday);
            if (dao.getpersonaldata(1).gender == 1) {
                radioGroup_sex.check(R.id.radioButton_boy);
            } else if (dao.getpersonaldata(1).gender == 0) {
                radioGroup_sex.check(R.id.radioButton_girl);
            }

            switch (dao.getpersonaldata(1).cityname) {
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
        catch (Exception e)
        {

        }
    }

    //解出照片格式
    public static Bitmap getFitImage(InputStream is)
    {
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
    public void loadphoto()
    {
        File f = new File(getExternalFilesDir("PHOTO"), "myphoto.jpg");
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









}
