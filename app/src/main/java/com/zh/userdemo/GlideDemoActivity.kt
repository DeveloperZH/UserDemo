package com.zh.userdemo

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.zh.commonuilibrary.util.transform.GlideBlurTransformation

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
        Glide.with(this)
                .load(url_1)
                .transform(CircleCrop())
                .into(findViewById(R.id.iv_1))

        Glide.with(this)
                .load(url_1)
                .transform(RoundedCorners(20))
                .into(findViewById(R.id.iv_2))

        Glide.with(this)
                .load(url_1)
                .transform(GlideBlurTransformation(this,15))
                .into(findViewById(R.id.iv_3))
    }
}