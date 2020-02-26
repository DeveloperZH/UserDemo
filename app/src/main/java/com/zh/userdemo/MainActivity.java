package com.zh.userdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.xtv_text).setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this,XTextViewActivity.class));
        });
    }
}
