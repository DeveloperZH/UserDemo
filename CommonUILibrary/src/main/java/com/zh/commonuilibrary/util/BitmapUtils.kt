package com.zh.commonuilibrary.util

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

/**
 * @describe:
 * @author: Z H
 * @date: 2020/3/23 13:33
 * @pkgName: com.zh.commonuilibrary.util
 */


/**
 * @param image      需要模糊的图片
 * @param blurRadius 模糊的半径（1-25之间）
 * @return 模糊处理后的Bitmap
 */
fun blurBitmap(context: Context, image: Bitmap, blurRadius: Int, outWidth: Int, outHeight: Int): Bitmap {
    val inputBitmap = Bitmap.createScaledBitmap(image, outWidth, outHeight, false)
    // 创建一张渲染后的输出图片
    val outputBitmap = Bitmap.createBitmap(inputBitmap)
    // 创建RenderScript内核对象
    val rs = RenderScript.create(context)
    // 创建一个模糊效果的RenderScript的工具对象
    val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
    // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
    // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
    val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
    val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
    // 设置渲染的模糊程度, 25f是最大模糊度
    blurScript.setRadius(blurRadius.toFloat())
    // 设置blurScript对象的输入内存
    blurScript.setInput(tmpIn)
    // 将输出数据保存到输出内存中
    blurScript.forEach(tmpOut)
    // 将数据填充到Allocation中
    tmpOut.copyTo(outputBitmap)
    return outputBitmap
}