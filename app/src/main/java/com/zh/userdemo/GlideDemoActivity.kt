package com.zh.userdemo

import android.widget.ImageView
import com.zh.commonuilibrary.util.GlideUtil

/**
 * @describe: Glide 用法测试
 * @author: Z H
 * @date: 2020/3/20 10:17
 * @pkgName: com.zh.userdemo
 */
class GlideDemoActivity : BaseActivity() {

    val url_1 = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg"

    override fun layoutResId(): Int = R.layout.activity_glide

    override fun initData() {
        GlideUtil.setCircleImg(this,url_1, findViewById<ImageView>(R.id.iv_1))
        GlideUtil.setRoundedCornerImg(this,url_1, findViewById<ImageView>(R.id.iv_2),20)
    }
}