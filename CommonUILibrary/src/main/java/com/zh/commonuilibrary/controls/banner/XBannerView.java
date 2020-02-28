package com.zh.commonuilibrary.controls.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.zh.commonuilibrary.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @describe:
 * @author: Z H
 * @date: 2020/2/27 16:23
 * @pkgName: com.zh.commonuilibrary.controls.banner
 */
public class XBannerView extends RelativeLayout {

    private RecyclerView rvBanner;

    public XBannerView(Context context) {
        this(context, null);
    }

    public XBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_root_banner, null, true);
        addView(view);
        rvBanner = view.findViewById(R.id.rv_banner);
        rvBanner.setLayoutManager(new LinearLayoutManager(context));
    }
}
