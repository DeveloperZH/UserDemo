package com.zh.userdemo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.zh.commonuilibrary.controls.DrawableBuilder;
import com.zh.commonuilibrary.controls.XDrawable;
import com.zh.commonuilibrary.controls.XTextView;

/**
 * @describe: DemoActivity
 * @author: Z H
 * @date: 2020/2/26 10:25
 * @pkgName: com.zh.userdemo
 */
public class XTextViewActivity extends BaseActivity {

    private XTextView xtv_text_1;
    private XTextView xtv_text_2;
    private XTextView xtv_text_3;
    private XTextView xtv_text_4;
    private boolean isSelect = false;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_xtextview;
    }

    @Override
    public void initView() {
        xtv_text_1 = findViewById(R.id.xtv_text_1);
        xtv_text_2 = findViewById(R.id.xtv_text_2);
        xtv_text_3 = findViewById(R.id.xtv_text_3);
        xtv_text_4 = findViewById(R.id.xtv_text_4);
    }

    @Override
    public void initData() {
        setXtvInActivity_1();
        setXtvInActivity_2();
        setXTvState(xtv_text_3, android.R.attr.state_pressed);
        setXTvState(xtv_text_4, android.R.attr.state_selected);

        xtv_text_4.setOnClickListener(v -> {
            isSelect = !isSelect;
            xtv_text_4.setSelected(isSelect);
        });
    }


    /**
     * 在代码中通过设置XTextView.setxx()方法设置属性
     */
    private void setXtvInActivity_1() {
        xtv_text_1.setShape(DrawableBuilder.RECTANGLE)
                .setBackGroundColor(Color.RED, 30)
                .setCornerRadius(60)
                .setLine(5, Color.YELLOW, 70)
                .setDotted(5, 1);
    }

    /**
     * 在代码中通过设置XTextView.setxx()方法设置属性
     */
    private void setXtvInActivity_2() {
        Drawable drawable = new DrawableBuilder()
                .shape(DrawableBuilder.RECTANGLE)
                .backGroundColor(Color.RED, 80)
                .cornerRadius(120)
                .line(5, Color.YELLOW, 70)
                .build();
        xtv_text_2.setBackground(drawable);
    }

    /**
     * 设置XTextView的按压选中效果
     */
    private void setXTvState(XTextView xTextView, int state) {
        XDrawable drawableBuilder = new DrawableBuilder().backGroundColor(Color.YELLOW)
                .shape(DrawableBuilder.RECTANGLE)
                .line(10, Color.LTGRAY)
                .textColor(R.color.colorAccent)
                .build();
        XDrawable drawableBuilder2 = new DrawableBuilder().backGroundColor(Color.GREEN)
                .shape(DrawableBuilder.OVAL)
                .line(10, Color.YELLOW)
                .textColor(R.color.color_1e68ff)
                .build();
        xTextView.setDrawableState(drawableBuilder, drawableBuilder2, state);
    }
}
