package com.zh.commonuilibrary.util

import android.content.Context
import android.util.TypedValue

/**
 * @describe: 设备尺寸像素相关
 * @author: Z H
 * @date: 2020/3/2 16:27
 * @pkgName: com.zh.commonuilibrary.util
 */
fun dp2px(context: Context, dpVal: Float): Int {
    return TypedValue.applyDimension(1, dpVal, context.resources.displayMetrics).toInt()
}

fun sp2px(context: Context, spVal: Float): Int {
    return TypedValue.applyDimension(2, spVal, context.resources.displayMetrics).toInt()
}

fun px2dp(context: Context, pxVal: Float): Float {
    val scale = context.resources.displayMetrics.density
    return pxVal / scale
}

fun px2sp(context: Context, pxVal: Float): Float {
    return pxVal / context.resources.displayMetrics.scaledDensity
}

fun getPhoneWidth(context: Context): Int {
    val dm = context.resources.displayMetrics
    return dm.widthPixels
}

fun getPhoneHeight(context: Context): Int {
    val dm = context.resources.displayMetrics
    return dm.heightPixels
}