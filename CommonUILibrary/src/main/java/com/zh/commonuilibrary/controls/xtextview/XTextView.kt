package com.zh.commonuilibrary.controls.xtextview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.annotation.ColorInt
import com.zh.commonuilibrary.R
import com.zh.commonuilibrary.util.ColorUtil

/**
 * @describe: XTextView
 * 用XTextView 在xml种设置android自带的background属性会失效
 * 如果需要用该属性  只能在代码中使用
 * @author: Z H
 * @date: 2020/2/26 10:16
 * @pkgName: com.zh.commonuilibrary.controls
 */
@SuppressLint("AppCompatCustomView")
class XTextView : TextView {
    private val mBuilder: DrawableBuilder = DrawableBuilder()
    private var shape = 0
    private var lineWidth = 0
    private var lineColorAlpha = 100
    private var lineColor = Color.WHITE
    private var dashWidth = 0f
    private var dashGap = 0f
    private var cornerRadius = 0f
    private var bgColor = Color.WHITE
    private var bgColorAlpha = 100

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init(attributeSet)
    }


    @SuppressLint("ResourceType")
    private fun init(attrs: AttributeSet?) {
        @SuppressLint("Recycle") val typedArray = context.obtainStyledAttributes(attrs, R.styleable.XTextView)
        shape = typedArray.getInt(R.styleable.XTextView_shape, 0)
        lineWidth = typedArray.getInt(R.styleable.XTextView_lineWidth, 0)
        lineColor = typedArray.getColor(R.styleable.XTextView_lineColor, Color.TRANSPARENT)
        lineColorAlpha = typedArray.getInt(R.styleable.XTextView_lineColorAlpha, lineColorAlpha)
        dashWidth = typedArray.getFloat(R.styleable.XTextView_dashWidth, 0f)
        dashGap = typedArray.getFloat(R.styleable.XTextView_dashGap, 0f)
        cornerRadius = typedArray.getFloat(R.styleable.XTextView_radius, 0f)
        bgColor = typedArray.getColor(R.styleable.XTextView_bgColor, bgColor)
        bgColorAlpha = typedArray.getInt(R.styleable.XTextView_bgColorAlpha, bgColorAlpha)
        typedArray.recycle()
        setShape(shape)
        setLine(lineWidth, lineColor, lineColorAlpha)
        setDotted(dashWidth, dashGap)
        setCornerRadius(cornerRadius)
        setBackGroundColor(bgColor, bgColorAlpha)
    }

    /**
     * 设置类型
     *
     * @param shape shape的类型
     * [DrawableBuilder.Shape]
     */
    fun setShape(@DrawableBuilder.Shape shape: Int): XTextView {
        this.shape = shape
        mBuilder.shape(shape)
        this.background = mBuilder.build()
        return this
    }

    /**
     * 设置边框
     *
     * @param lineWidth 边框的宽度
     * @param lineColor 边框的颜色
     */
    fun setLine(lineWidth: Int, lineColor: Int): XTextView {
        this.lineWidth = lineWidth
        this.lineColor = lineColor
        mBuilder.line(lineWidth, lineColor)
        this.background = mBuilder.build()
        return this
    }

    /**
     * 设置边框
     *
     * @param lineWidth      边框的宽度
     * @param lineColor      边框的颜色
     * @param lineColorAlpha 边框色值不透明度  0~100
     */
    fun setLine(lineWidth: Int, lineColor: Int, lineColorAlpha: Int): XTextView {
        this.lineWidth = lineWidth
        this.lineColorAlpha = lineColorAlpha
        this.lineColor = ColorUtil.getColorByAlpha(lineColor, lineColorAlpha)
        mBuilder.line(lineWidth, lineColor)
        this.background = mBuilder.build()
        return this
    }

    /**
     * 虚线
     * 调用该方法之前需先调用 [.setLine] 方法设置边框width和color
     *
     * @param mDashGap   每一小段虚线的长度
     * @param mDashWidth 虚线之间间隙的长度
     */
    fun setDotted(mDashWidth: Float, mDashGap: Float): XTextView {
        dashWidth = mDashWidth
        dashGap = mDashGap
        mBuilder.dottedLine(lineWidth, lineColor, mDashWidth, mDashGap)
        this.background = mBuilder.build()
        return this
    }

    /**
     * 设置圆角
     */
    fun setCornerRadius(cornerRadius: Float): XTextView {
        this.cornerRadius = cornerRadius
        mBuilder.cornerRadius(cornerRadius)
        this.background = mBuilder.build()
        return this
    }

    /**
     * 设置圆角
     * radii 数组分别指定四个圆角的半径，每个角可以指定[X_Radius,Y_Radius]，
     * 四个圆角的顺序为左上，右上，右下，左下。如果X_Radius,Y_Radius为0表示还是直角。
     */
    fun setCornerRadius(cornerRadius: FloatArray): XTextView {
        mBuilder.cornerRadius(cornerRadius)
        this.background = mBuilder.build()
        return this
    }

    /**
     * 设置背景色
     */
    fun setBackGroundColor(@ColorInt bgColor: Int): XTextView {
        this.bgColor = bgColor
        mBuilder.backGroundColor(bgColor)
        this.background = mBuilder.build()
        return this
    }

    /**
     * 设置背景色 带透明度
     * 0 <= color <= 16777215
     *
     * @param bgColorAlpha 色值不透明度  0~100
     * 0 完全透明   1 为完全不透明
     */
    fun setBackGroundColor(@ColorInt bgColor: Int, bgColorAlpha: Int): XTextView {
        this.bgColor = bgColor
        this.bgColorAlpha = bgColorAlpha
        mBuilder.backGroundColor(bgColor, bgColorAlpha)
        this.background = mBuilder.build()
        return this
    }

    /**
     * @param commonDrawable 普通状态
     * @param selectDrawable state 为true的状态
     * @param type           为可绘制对象指定一组状态
     * 例如
     * [android.R.attr.state_selected],
     * [android.R.attr.state_pressed]
     */
    fun setDrawableState(commonDrawable: XDrawable, selectDrawable: XDrawable, type: Int): XTextView {
        this.isClickable = true
        val listDrawable = StateListDrawable()
        listDrawable.addState(intArrayOf(type), commonDrawable)
        listDrawable.addState(intArrayOf(-type), selectDrawable)
        try {
            val colors = intArrayOf(resources.getColor(commonDrawable.textColor), resources.getColor(selectDrawable.textColor))
            val colorStates = arrayOfNulls<IntArray>(2)
            colorStates[0] = intArrayOf(type)
            colorStates[1] = intArrayOf()
            val colorList = ColorStateList(colorStates, colors)
            this.setTextColor(colorList)
        } catch (e: Exception) {
            Log.e("Exception", "设置按压文字变色 必须两个XDrawable 都要设置textColor")
        }
        this.background = listDrawable
        return this
    }


}