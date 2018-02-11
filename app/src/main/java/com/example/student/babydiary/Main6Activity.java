package com.example.student.babydiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.babydiary.data.AlldataDAO;

public class Main6Activity extends AppCompatActivity {
    Spinner sp;
    WebView wb;
    public static AlldataDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        dao = new AlldataDAO(Main6Activity.this);
        sp= (Spinner)findViewById(R.id.spinner_support1);
        wb = (WebView)findViewById(R.id.webView);
        //sp2= (Spinner)findViewById(R.id.spinner_support2);
        //sp3= (Spinner)findViewById(R.id.spinner_support3);
        final String[] city = {"台北市","新北市","基隆市","宜蘭縣","桃園市","新竹縣","新竹市","苗栗縣","花蓮縣","台東縣"
                ,"台中市","彰化縣","南投縣","雲林縣","嘉義縣","嘉義市","台南市","高雄市","屏東縣","澎湖縣","金門縣","連江縣"
        };
        ArrayAdapter cityList = new ArrayAdapter<>(Main6Activity.this,
                R.layout.myspinner,city
        );
        cityList.setDropDownViewResource(R.layout.myspinner);

        sp.setAdapter(cityList);
        reload();
        sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main6Activity.this, " 您的戶籍地是:"+sp.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                WebSettings settings = wb.getSettings();
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);


                wb.getSettings().setJavaScriptEnabled(true);
                int i=0;
                switch(position)
                {
                    case 0:wb.loadUrl("file:///android_asset/www/tpe.html");
                        break;
                    case 1:wb.loadUrl("file:///android_asset/www/newtai.html");
                        break;
                    case 2:wb.loadUrl("file:///android_asset/www/keelung.html");
                        break;
                    case 3:wb.loadUrl("file:///android_asset/www/yilan.html");
                        break;
                    case 4:wb.loadUrl("file:///android_asset/www/taoyuan.html");
                        break;
                    case 5:wb.loadUrl("file:///android_asset/www/xinzhucounty.html");
                        break;
                    case 6:wb.loadUrl("file:///android_asset/www/xinzhucity.html");
                        break;
                    case 7:wb.loadUrl("file:///android_asset/www/miaoli.html");
                        break;
                    case 8:wb.loadUrl("file:///android_asset/www/hualian.html");
                        break;
                    case 9:wb.loadUrl("file:///android_asset/www/taidong.html");
                        break;
                    case 10:wb.loadUrl("file:///android_asset/www/taichong.html");
                        break;
                    case 11:wb.loadUrl("file:///android_asset/www/zhanghua.html");
                        break;
                    case 12:wb.loadUrl("file:///android_asset/www/nantou.html");
                        break;
                    case 13:wb.loadUrl("file:///android_asset/www/yunlin.html");
                        break;
                    case 14:wb.loadUrl("file:///android_asset/www/jiayicounty.html");
                        break;
                    case 15:wb.loadUrl("file:///android_asset/www/jiayicity.html");
                        break;
                    case 16:wb.loadUrl("file:///android_asset/www/tainan.html");
                        break;
                    case 17:wb.loadUrl("file:///android_asset/www/kaohsiung.html");
                        break;
                    case 18:wb.loadUrl("file:///android_asset/www/pingtung.html");
                        break;
                    case 19:wb.loadUrl("file:///android_asset/www/penghu.html");
                        break;
                    case 20:wb.loadUrl("file:///android_asset/www/kinmen.html");
                        break;
                    case 21:wb.loadUrl("file:///android_asset/www/mazu.html");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }


    //資料庫有資料時代入資料
    public void reload()
    {
        try {
            //資料庫有資料時代入資料

            switch (dao.getpersonaldata(1).cityname) {
                case 0:
                    sp.setSelection(0);
                    break;
                case 1:
                    sp.setSelection(1);
                    break;
                case 2:
                    sp.setSelection(2);
                    break;
                case 3:
                    sp.setSelection(3);
                    break;
                case 4:
                    sp.setSelection(4);
                    break;
                case 5:
                    sp.setSelection(5);
                    break;
                case 6:
                    sp.setSelection(6);
                    break;
                case 7:
                    sp.setSelection(7);
                    break;
                case 8:
                    sp.setSelection(8);
                    break;
                case 9:
                    sp.setSelection(9);
                    break;
                case 10:
                    sp.setSelection(10);
                    break;
                case 11:
                    sp.setSelection(11);
                    break;
                case 12:
                    sp.setSelection(12);
                    break;
                case 13:
                    sp.setSelection(13);
                    break;
                case 14:
                    sp.setSelection(14);
                    break;
                case 15:
                    sp.setSelection(15);
                    break;
                case 16:
                    sp.setSelection(16);
                    break;
                case 17:
                    sp.setSelection(17);
                    break;
                case 18:
                    sp.setSelection(18);
                    break;
                case 19:
                    sp.setSelection(19);
                    break;
                case 20:
                    sp.setSelection(20);
                    break;
                case 21:
                    sp.setSelection(21);
                    break;
            }
        }
        catch (Exception e)
        {

        }
    }
}
