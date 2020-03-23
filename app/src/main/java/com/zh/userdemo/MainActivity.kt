package com.zh.userdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        xtv_text.setOnClickListener { v: View? -> startActivity(Intent(this@MainActivity, XTextViewActivity::class.java)) }

        xtv_banner.setOnClickListener {
           startActivity(Intent(this@MainActivity,GlideDemoActivity::class.java))
        }
    }


}


