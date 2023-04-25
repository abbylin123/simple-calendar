package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.FileOutputStream;

public class MainActivity3 extends AppCompatActivity {
    String string = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText edt = findViewById(R.id.editTextTextPersonName3);
        Intent it = getIntent();//印出檔案
        String str = it.getStringExtra("name2");
        edt.setSingleLine(false);
        edt.setHorizontallyScrolling(false);
        if(str.length()>=3){//長度大於=3(\n)
            edt.setText(str);
        }
        else {
            edt.setText("無記事");
        }
        string = str;
    }
    public void btn2_Click(View v){//確定刪除
        //寫檔案
        EditText edt = findViewById(R.id.editTextTextPersonName3);
        String str = edt.getText().toString();
        String filename = "file";
        String fileContents = str;
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();//回上一頁
    }
    public void btn3_Click(View v) {//取消
        //寫檔案
        EditText edt = findViewById(R.id.editTextTextPersonName3);
        String filename = "file";
        String fileContents = string;
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();//回上一頁
    }
}