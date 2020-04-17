package com.zh.userdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

/**
 * @describe:
 * @author: Z H
 * @date: 2020/2/26 10:25
 * @pkgName: com.zh.userdemo
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun layoutResId(): Int
    abstract fun initData()
    open fun initEvent() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val rootView = findViewById<FrameLayout>(R.id.root_view)
        val mView = LayoutInflater.from(this).inflate(layoutResId(), null)
        rootView.addView(mView)
        initData()
        initEvent()
    }
}