package com.zh.commonuilibrary.controls;

import android.graphics.Color;

import com.zh.commonuilibrary.util.ColorUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IntDef;


/**
 * @describe: ${todo}
 * @author: Z H
 * @date: 2019/9/5 10:34
 * @pkgName: shapedemo.zh.com.shapedemo.my
 */
public class DrawableBuilder {
    private int mShape;
    private float[] mCornerRadius;
    private int mLineWidth;
    private int mLineColor;
    private float mDashWidth;
    private float mDashGap;
    private int mbgColor = Color.TRANSPARENT;
    private @ColorRes int textColor;

    public static final int RECTANGLE = 0;
    public static final int OVAL = 1;
    public static final int LINE = 2;
    public static final int RING = 3;

    @IntDef({RECTANGLE, OVAL, LINE, RING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
    }

    public DrawableBuilder() {

    }

    public XDrawable build() {
        XDrawable gradientDrawable = new XDrawable();
        gradientDrawable.setShape(mShape);
        gradientDrawable.setStroke(mLineWidth, mLineColor, mDashWidth, mDashGap);
        gradientDrawable.setCornerRadii(mCornerRadius);
        gradientDrawable.setColor(mbgColor);
        gradientDrawable.setTextColor(textColor);
        return gradientDrawable;
    }


    public DrawableBuilder shape(@Shape int shape) {
        this.mShape = shape;
        return this;
    }


    /**
     * 设置边框
     *
     * @param lineWidth 边框宽度  单位px
     * @param lineColor 边框的颜色
     */
    public DrawableBuilder line(int lineWidth, int lineColor) {
        this.mLineWidth = lineWidth;
        this.mLineColor = lineColor;
        return this;
    }

    /**
     * 设置边框
     *
     * @param lineWidth      边框宽度  单位px
     * @param lineColor      边框的颜色
     * @param lineColorAlpha 边框颜色的透明度
     */
    public DrawableBuilder line(int lineWidth, int lineColor, int lineColorAlpha) {
        this.mLineWidth = lineWidth;
        this.mLineColor = ColorUtil.getColorByAlpha(lineColor, lineColorAlpha);
        return this;
    }

    /**
     * 虚线
     *
     * @param lineWidth  边框宽度  单位px
     * @param lineColor  边框的颜色
     * @param mDashGap   虚线的长度 单位px
     * @param mDashWidth 虚线之间间隙的长度 单位px
     */
    public DrawableBuilder dottedLine(int lineWidth, @ColorInt int lineColor, float mDashWidth, float mDashGap) {
        this.mLineWidth = lineWidth;
        this.mLineColor = lineColor;
        this.mDashWidth = mDashWidth;
        this.mDashGap = mDashGap;
        return this;
    }

    /**
     * 设置圆角
     *
     * @param cornerRadius 单位 px
     *                     如果要分别设置上下左右
     * @see #cornerRadius(float[])
     */
    public DrawableBuilder cornerRadius(float cornerRadius) {
        this.mCornerRadius = new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius};
        return this;
    }

    /**
     * radii 数组分别指定四个圆角的半径，每个角可以指定[X_Radius,Y_Radius]，
     * 四个圆角的顺序为左上，右上，右下，左下。如果X_Radius,Y_Radius为0表示还是直角。
     */
    public DrawableBuilder cornerRadius(float[] mCornerRadius) {
        this.mCornerRadius = mCornerRadius;
        return this;
    }


    /**
     * 设置背景色
     */
    public DrawableBuilder backGroundColor(@ColorInt int mbgColor) {
        this.mbgColor = mbgColor;
        return this;
    }


    /**
     * 设置背景色 带透明度
     * 0 <= color <= 16777215
     *
     * @param alpha 色值不透明度  0~100
     *              0 完全透明   1 为完全不透明
     */
    public DrawableBuilder backGroundColor(int mbgColor, int alpha) {
        this.mbgColor = ColorUtil.getColorByAlpha(mbgColor, alpha);
        return this;
    }


    /**
     * 这个是配合 {@link XTextView#setDrawableState(XDrawable, XDrawable, int)}
     * 这个方法来设置不同的state的文字颜色
     * 文字不需要变色 可以不设置
     */
    public DrawableBuilder textColor(@ColorRes int textColor) {
        this.textColor = textColor;
        return this;
    }
}
