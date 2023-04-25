package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txv = findViewById(R.id.textView6);
        Intent it = getIntent();//印出
        String str = it.getStringExtra("name");
        if(str.length()>=3){//長度大於=3(\n)
            txv.setText(str);
        }
        else {
            txv.setText("無記事");
        }
    }
    public void btn_Click(View v){//回到上一頁
        finish();
    }
}