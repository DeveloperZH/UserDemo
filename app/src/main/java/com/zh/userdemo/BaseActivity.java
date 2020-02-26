package com.zh.userdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @describe:
 * @author: Z H
 * @date: 2020/2/26 10:25
 * @pkgName: com.zh.userdemo
 */
public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayoutResId();
    public abstract void initView();
    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        FrameLayout rootView = findViewById(R.id.root_view);
        View mView = LayoutInflater.from(this).inflate(getLayoutResId(),null);
        rootView.addView(mView);

        initView();
        initData();
    }
}
