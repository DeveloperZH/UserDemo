package com.zh.commonuilibrary.controls.xtextview;

import android.graphics.drawable.GradientDrawable;

import androidx.annotation.ColorRes;

/**
 * @describe: 用于GradientDrawable的扩展
 * @author: Z H
 * @date: 2020/2/27 13:38
 * @pkgName: com.zh.commonuilibrary.controls
 */
public class XDrawable extends GradientDrawable {

    private int textColor;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(@ColorRes int textColor) {
        this.textColor = textColor;
    }


}
