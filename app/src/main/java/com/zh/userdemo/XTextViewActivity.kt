package com.zh.userdemo

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import com.zh.commonuilibrary.controls.xtextview.DrawableBuilder
import com.zh.commonuilibrary.controls.xtextview.XTextView
import kotlinx.android.synthetic.main.activity_xtextview.*

/**
 * @describe: DemoActivity
 * @author: Z H
 * @date: 2020/2/26 10:25
 * @pkgName: com.zh.userdemo
 */
class XTextViewActivity : BaseActivity() {

    private var isSelect = false
    override fun layoutResId(): Int {
        return R.layout.activity_xtextview
    }


    override fun initData() {
        setXtvInActivity_1()
        setXtvInActivity_2()
        setXTvState(xtv_text_3, android.R.attr.state_pressed)
        setXTvState(xtv_text_4, android.R.attr.state_selected)
        xtv_text_4.setOnClickListener { v: View? ->
            isSelect = !isSelect
            xtv_text_4.isSelected = isSelect
        }
    }

    /**
     * 在代码中通过设置XTextView.setxx()方法设置属性
     */
    private fun setXtvInActivity_1() {
        xtv_text_1.setShape(DrawableBuilder.RECTANGLE)
                .setBackGroundColor(Color.RED, 30)
                .setCornerRadius(60f)
                .setLine(5, Color.YELLOW, 70)
                .setDotted(5f, 1f)
    }

    /**
     * 在代码中通过设置XTextView.setxx()方法设置属性
     */
    private fun setXtvInActivity_2() {
        val drawable: Drawable = DrawableBuilder()
                .shape(DrawableBuilder.RECTANGLE)
                .backGroundColor(Color.RED, 80)
                .cornerRadius(120f)
                .line(5, Color.YELLOW, 70)
                .build()
        xtv_text_2.background = drawable
    }

    /**
     * 设置XTextView的按压选中效果
     */
    private fun setXTvState(xTextView: XTextView?, state: Int) {
        val drawableBuilder = DrawableBuilder().backGroundColor(Color.YELLOW)
                .shape(DrawableBuilder.RECTANGLE)
                .line(10, Color.LTGRAY)
                .textColor(R.color.colorAccent)
                .build()
        val drawableBuilder2 = DrawableBuilder().backGroundColor(Color.GREEN)
                .shape(DrawableBuilder.OVAL)
                .line(10, Color.YELLOW)
                .textColor(R.color.color_1e68ff)
                .build()
        xTextView!!.setDrawableState(drawableBuilder, drawableBuilder2, state)
    }
}