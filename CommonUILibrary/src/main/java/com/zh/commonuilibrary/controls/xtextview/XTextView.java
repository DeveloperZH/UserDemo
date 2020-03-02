package com.zh.commonuilibrary.controls.xtextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.zh.commonuilibrary.R;
import com.zh.commonuilibrary.util.ColorUtil;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * @describe: XTextView
 * @author: Z H
 * @date: 2020/2/26 10:16
 * @pkgName: com.zh.commonuilibrary.controls
 */
@SuppressLint("AppCompatCustomView")
public class XTextView extends TextView {


    /**
     * 用XTextView 在xml种设置android自带的background属性会失效
     * 如果需要用该属性  只能在代码中使用
     */
    private Context context;
    private DrawableBuilder mBuilder;
    private int shape;
    private int lineWidth;
    private int lineColorAlpha = 100;
    private int lineColor = Color.WHITE;
    private float dashWidth;
    private float dashGap;
    private float cornerRadius;
    private int bgColor = Color.WHITE;
    private int bgColorAlpha = 100;


    public XTextView(Context context) {
        this(context, null);
    }

    public XTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mBuilder = new DrawableBuilder();
        init(attrs);
    }


    @SuppressLint("ResourceType")
    private void init(AttributeSet attrs) {
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XTextView);
        shape = typedArray.getInt(R.styleable.XTextView_shape, 0);
        lineWidth = typedArray.getInt(R.styleable.XTextView_lineWidth, 0);
        lineColor = typedArray.getColor(R.styleable.XTextView_lineColor, Color.TRANSPARENT);
        lineColorAlpha = typedArray.getInt(R.styleable.XTextView_lineColorAlpha, lineColorAlpha);
        dashWidth = typedArray.getFloat(R.styleable.XTextView_dashWidth, 0);
        dashGap = typedArray.getFloat(R.styleable.XTextView_dashGap, 0);
        cornerRadius = typedArray.getFloat(R.styleable.XTextView_radius, 0);
        bgColor = typedArray.getColor(R.styleable.XTextView_bgColor, bgColor);
        bgColorAlpha = typedArray.getInt(R.styleable.XTextView_bgColorAlpha, bgColorAlpha);
        typedArray.recycle();

        setShape(shape);
        setLine(lineWidth, lineColor, lineColorAlpha);
        setDotted(dashWidth, dashGap);
        setCornerRadius(cornerRadius);
        setBackGroundColor(bgColor, bgColorAlpha);
    }

    /**
     * 设置类型
     *
     * @param shape shape的类型
     *              {@link DrawableBuilder.Shape}
     */
    public XTextView setShape(@DrawableBuilder.Shape int shape) {
        this.shape = shape;
        mBuilder.shape(shape);
        this.setBackground(mBuilder.build());
        return this;
    }

    /**
     * 设置边框
     *
     * @param lineWidth 边框的宽度
     * @param lineColor 边框的颜色
     */
    public XTextView setLine(int lineWidth, int lineColor) {
        this.lineWidth = lineWidth;
        this.lineColor = lineColor;
        mBuilder.line(lineWidth, lineColor);
        this.setBackground(mBuilder.build());
        return this;
    }

    /**
     * 设置边框
     *
     * @param lineWidth      边框的宽度
     * @param lineColor      边框的颜色
     * @param lineColorAlpha 边框色值不透明度  0~100
     */
    public XTextView setLine(int lineWidth, int lineColor, int lineColorAlpha) {
        this.lineWidth = lineWidth;
        this.lineColorAlpha = lineColorAlpha;
        this.lineColor = ColorUtil.getColorByAlpha(lineColor, lineColorAlpha);
        mBuilder.line(lineWidth, lineColor);
        this.setBackground(mBuilder.build());
        return this;
    }

    /**
     * 虚线
     * 调用该方法之前需先调用 {@link #setLine(int, int)} 方法设置边框width和color
     *
     * @param mDashGap   每一小段虚线的长度
     * @param mDashWidth 虚线之间间隙的长度
     */
    public XTextView setDotted(float mDashWidth, float mDashGap) {
        this.dashWidth = mDashWidth;
        this.dashGap = mDashGap;
        mBuilder.dottedLine(lineWidth, lineColor, mDashWidth, mDashGap);
        this.setBackground(mBuilder.build());
        return this;
    }

    /**
     * 设置圆角
     */
    public XTextView setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        mBuilder.cornerRadius(cornerRadius);
        this.setBackground(mBuilder.build());
        return this;
    }

    /**
     * 设置圆角
     * radii 数组分别指定四个圆角的半径，每个角可以指定[X_Radius,Y_Radius]，
     * 四个圆角的顺序为左上，右上，右下，左下。如果X_Radius,Y_Radius为0表示还是直角。
     */
    public XTextView setCornerRadius(float[] cornerRadius) {
        mBuilder.cornerRadius(cornerRadius);
        this.setBackground(mBuilder.build());
        return this;
    }

    /**
     * 设置背景色
     */
    public XTextView setBackGroundColor(@ColorInt int bgColor) {
        this.bgColor = bgColor;
        mBuilder.backGroundColor(bgColor);
        this.setBackground(mBuilder.build());
        return this;
    }

    /**
     * 设置背景色 带透明度
     * 0 <= color <= 16777215
     *
     * @param bgColorAlpha 色值不透明度  0~100
     *                     0 完全透明   1 为完全不透明
     */
    public XTextView setBackGroundColor(@ColorInt int bgColor, int bgColorAlpha) {
        this.bgColor = bgColor;
        this.bgColorAlpha = bgColorAlpha;
        mBuilder.backGroundColor(bgColor, bgColorAlpha);
        this.setBackground(mBuilder.build());
        return this;
    }

    /**
     * @param commonDrawable 普通状态
     * @param selectDrawable state 为true的状态
     * @param type           为可绘制对象指定一组状态
     *                       例如
     *                       {@link android.R.attr#state_selected},
     *                       {@link android.R.attr#state_pressed}
     */
    public XTextView setDrawableState(XDrawable commonDrawable, XDrawable selectDrawable, int type) {
        this.setClickable(true);
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{type}, commonDrawable);
        listDrawable.addState(new int[]{-type}, selectDrawable);
        try {
            int[] colors = new int[]{getResources().getColor(commonDrawable.getTextColor()), getResources().getColor(selectDrawable.getTextColor())};
            int[][] colorStates = new int[2][];
            colorStates[0] = new int[]{type};
            colorStates[1] = new int[]{};
            ColorStateList colorList = new ColorStateList(colorStates, colors);
            this.setTextColor(colorList);
        } catch (Exception e) {
            Log.e("Exception", "设置按压文字变色 必须两个XDrawable 都要设置textColor");
        }
        this.setBackground(listDrawable);
        return this;
    }
}
