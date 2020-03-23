package com.zh.commonuilibrary.util.transform

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.zh.commonuilibrary.util.blurBitmap
import java.security.MessageDigest


/**
 * @describe: glide设置图片模糊的Transformation
 * @author: Z H
 * @date: 2020/3/23 13:45
 * @pkgName: com.zh.commonuilibrary.util.transform
 */
class GlideBlurTransformation(private val context: Context,private var blurRadius:Int) : BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val bitmap  = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
        return blurBitmap(context, bitmap, blurRadius, (outWidth * 0.5).toInt(), (outHeight * 0.5).toInt())
    }
}