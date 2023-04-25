package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    String day,time;
    int a = 0,b = 0;
    private Object CalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //日曆
        CalendarView = (CalendarView) findViewById(R.id.calendarView);
        TextView txvday = findViewById(R.id.textView4);
        txvday.setText("請選擇日期");
        TextView txvtime = findViewById(R.id.textView5);
        txvtime.setText("請選擇時間");
        Button btn2 = findViewById(R.id.button2);
        btn2.setVisibility(btn2.INVISIBLE);
        ((android.widget.CalendarView) CalendarView).setOnDateChangeListener(new CalendarView.OnDateChangeListener() {//監聽當日期改變
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {//Month從0算起
                TextView txvday = findViewById(R.id.textView4);
                TextView txvtime = findViewById(R.id.textView5);
                String date = year + "/" + (month+1) + "/" + dayOfMonth;
                txvday.setText("" + date);
                day = txvday.getText().toString();
                Button btn2 = findViewById(R.id.button2);
                if(txvtime.getText().toString().compareTo("請選擇時間")==0){
                    btn2.setVisibility(btn2.INVISIBLE);
                }
                else{
                    btn2.setVisibility(btn2.VISIBLE);
                }
            }
        });
    }
    public void timebtn_Click(View v){//時間選擇器
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Button btn2 = findViewById(R.id.button2);
                TextView txvday = findViewById(R.id.textView4);
                TextView txvtime = findViewById(R.id.textView5);
                txvtime.setText(""+hourOfDay+" : "+minute);
                time = txvtime.getText().toString();
                 if(txvday.getText().toString().compareTo("請選擇日期")==0){
                     btn2.setVisibility(btn2.INVISIBLE);
                 }
                 else{
                    btn2.setVisibility(btn2.VISIBLE);
                 }
            }
        }, 20, 33, true);
        timePickerDialog.show();
    }
    public void surebtn_Click(View v){//確定
        Button btn2 = findViewById(R.id.button2);
        btn2.setVisibility(btn2.INVISIBLE);
        FileInputStream in = null;//先讀之前的檔案在加新的資料進去，否則會被覆蓋掉原本的資料
        StringBuffer data = new StringBuffer();
        String string = "";
        try {
            in = openFileInput("file");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
                data.append("\r\n");
            }
            string = data.toString();
            in.close();
        } catch (Exception e) {
            ;
        }
        //寫檔案
        EditText edt = findViewById(R.id.editTextTextPersonName);
        String str = edt.getText().toString();
        if(str.isEmpty()){
            str = "無記事";
        }
        String filename = "file";
        String fileContents = string+"●日期 : "+day+" 時間 : "+time+" 記事 : "+str;
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView txvday = findViewById(R.id.textView4);
        txvday.setText("請選擇日期");
        TextView txvtime = findViewById(R.id.textView5);
        txvtime.setText("請選擇時間");
        edt.setText("");
    }
    public void readbtn_Click(View v){//查看記事
        if(a==0){
            Toast.makeText(this, "無記事", Toast.LENGTH_SHORT).show();
            a++;
        }
        Intent it = new Intent(this, MainActivity2.class);//跳到第二頁
        FileInputStream in = null;//讀檔案
        StringBuffer data = new StringBuffer();
        try {
            in = openFileInput("file");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
                data.append("\r\n");
            }
            it.putExtra("name", data.toString());
            startActivityForResult(it, 100);
            in.close();
        } catch (Exception e) {
            ;
        }
    }
    public void deleteorupdatebtn_Click(View v){//刪除更改記事
        if(b==0){
            Toast.makeText(this, "無記事", Toast.LENGTH_SHORT).show();
            b++;
        }
        Intent it = new Intent(this, MainActivity3.class);//跳到第三頁
        FileInputStream in = null;//讀檔案
        StringBuffer data = new StringBuffer();
        try {
            in = openFileInput("file");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
                data.append("\r\n");
            }
            it.putExtra("name2", data.toString());
            startActivityForResult(it, 100);
            in.close();
        } catch (Exception e) {
            ;
        }
    }
}