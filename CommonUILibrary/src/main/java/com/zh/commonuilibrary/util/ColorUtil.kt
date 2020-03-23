package com.zh.commonuilibrary.util

import android.graphics.Color
import android.text.TextUtils

/**
 * @describe:
 * @author: Z H
 * @date: 2020/2/26 17:51
 * @pkgName: com.zh.commonuilibrary.util
 */
object ColorUtil {
    /**
     * 通过色值和透明度获取 带透明度的色值
     */
    fun getColorByAlpha(color: Int, alpha: Int): Int {
        val temp = Math.round(255 * alpha * 1.0f / 100f)
        var alphaStr = Integer.toHexString(temp)
        if (!TextUtils.isEmpty(alphaStr) && alphaStr.length == 1) {
            alphaStr = "0$alphaStr"
        }
        val hexCode = String.format("#%06X", 16777215 and color)
        return Color.parseColor(hexCode.replace("#", "#$alphaStr"))
    }
}