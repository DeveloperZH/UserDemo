package com.zh.commonuilibrary.controls.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.zh.commonuilibrary.R;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/**
 * @describe:
 * @author: Z H
 * @date: 2020/2/27 16:23
 * @pkgName: com.zh.commonuilibrary.controls.banner
 */
public class XBannerView<T> extends RelativeLayout {

    private Context context;
    private RecyclerView rvBanner;
    private BannerAdapter mAdapter;

    public XBannerView(Context context) {
        this(context, null);
    }

    public XBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_root_banner, null, true);
        addView(view);
        rvBanner = view.findViewById(R.id.rv_banner);
        rvBanner.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    /**
     * 设置边距
     *
     * @param sideMargin 中间图片到屏幕两边的距离
     * @param pageMargin 如果一屏多个图片  表示中间图片到两边图片的距离
     */
    public void setMargin(int sideMargin, int pageMargin) {
        if (mAdapter == null) {
            throw new NullPointerException("请先调用setData()或者setAdapter()方法");
        }
        mAdapter.setMargin(sideMargin, pageMargin);
    }

    /**
     * 设置SnapHelper
     * 默认是没有SnapHelper
     */
    public void setSnapHelper(SnapHelper snapHelper) {
        if (snapHelper != null) {
            snapHelper.attachToRecyclerView(rvBanner);
        }
    }

    /**
     * 设置数据
     */
    public void setData(@NonNull List<T> dateList, @NonNull @LayoutRes int itemLayoutResId, XBannerCallback xBannerCallback) {
        mAdapter = new BannerAdapter(context, itemLayoutResId, dateList);
        mAdapter.setXBannerCallback(xBannerCallback);
        rvBanner.setAdapter(mAdapter);
    }


    /**
     * 用户自定义Adapter  可以实现一些自己的方法
     * 必须继承BannerAdapter
     */
    public void setAdapter(BannerAdapter adapter) {
        this.mAdapter = adapter;
        rvBanner.setAdapter(adapter);
    }

}
