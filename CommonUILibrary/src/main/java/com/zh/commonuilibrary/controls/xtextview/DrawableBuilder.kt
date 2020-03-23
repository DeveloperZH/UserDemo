package com.zh.commonuilibrary.controls.xtextview

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IntDef
import com.zh.commonuilibrary.util.getColorByAlpha

/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2019/9/5 10:34
 * @pkgName: shapedemo.zh.com.shapedemo.my
 */
class DrawableBuilder {
    private var mShape = 0
    private var mCornerRadius = FloatArray(8)
    private var mLineWidth = 0
    private var mLineColor = 0
    private var mDashWidth = 0f
    private var mDashGap = 0f
    private var mbgColor = Color.TRANSPARENT
    @ColorRes
    private var textColor = 0

    @IntDef(RECTANGLE, OVAL, LINE, RING)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class Shape

    fun build(): XDrawable {
        val gradientDrawable = XDrawable()
        gradientDrawable.shape = mShape
        gradientDrawable.setStroke(mLineWidth, mLineColor, mDashWidth, mDashGap)
        gradientDrawable.cornerRadii = mCornerRadius
        gradientDrawable.setColor(mbgColor)
        gradientDrawable.textColor = textColor
        return gradientDrawable
    }

    fun shape(@Shape shape: Int): DrawableBuilder {
        mShape = shape
        return this
    }

    /**
     * 设置边框
     *
     * @param lineWidth 边框宽度  单位px
     * @param lineColor 边框的颜色
     */
    fun line(lineWidth: Int, lineColor: Int): DrawableBuilder {
        mLineWidth = lineWidth
        mLineColor = lineColor
        return this
    }

    /**
     * 设置边框
     *
     * @param lineWidth      边框宽度  单位px
     * @param lineColor      边框的颜色
     * @param lineColorAlpha 边框颜色的透明度
     */
    fun line(lineWidth: Int, lineColor: Int, lineColorAlpha: Int): DrawableBuilder {
        mLineWidth = lineWidth
        mLineColor = getColorByAlpha(lineColor, lineColorAlpha)
        return this
    }

    /**
     * 虚线
     *
     * @param lineWidth  边框宽度  单位px
     * @param lineColor  边框的颜色
     * @param mDashGap   虚线的长度 单位px
     * @param mDashWidth 虚线之间间隙的长度 单位px
     */
    fun dottedLine(lineWidth: Int, @ColorInt lineColor: Int, mDashWidth: Float, mDashGap: Float): DrawableBuilder {
        mLineWidth = lineWidth
        mLineColor = lineColor
        this.mDashWidth = mDashWidth
        this.mDashGap = mDashGap
        return this
    }

    /**
     * 设置圆角
     *
     * @param cornerRadius 单位 px
     * 如果要分别设置上下左右
     * @see .cornerRadius
     */
    fun cornerRadius(cornerRadius: Float): DrawableBuilder {
        mCornerRadius = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius)
        return this
    }

    /**
     * radii 数组分别指定四个圆角的半径，每个角可以指定[X_Radius,Y_Radius]，
     * 四个圆角的顺序为左上，右上，右下，左下。如果X_Radius,Y_Radius为0表示还是直角。
     */
    fun cornerRadius(mCornerRadius: FloatArray): DrawableBuilder {
        this.mCornerRadius = mCornerRadius
        return this
    }

    /**
     * 设置背景色
     */
    fun backGroundColor(@ColorInt mbgColor: Int): DrawableBuilder {
        this.mbgColor = mbgColor
        return this
    }

    /**
     * 设置背景色 带透明度
     * 0 <= color <= 16777215
     *
     * @param alpha 色值不透明度  0~100
     * 0 完全透明   1 为完全不透明
     */
    fun backGroundColor(mbgColor: Int, alpha: Int): DrawableBuilder {
        this.mbgColor = getColorByAlpha(mbgColor, alpha)
        return this
    }

    /**
     * 这个是配合 [XTextView.setDrawableState]
     * 这个方法来设置不同的state的文字颜色
     * 文字不需要变色 可以不设置
     */
    fun textColor(@ColorRes textColor: Int): DrawableBuilder {
        this.textColor = textColor
        return this
    }

    companion object {
        const val RECTANGLE = 0
        const val OVAL = 1
        const val LINE = 2
        const val RING = 3
    }
}