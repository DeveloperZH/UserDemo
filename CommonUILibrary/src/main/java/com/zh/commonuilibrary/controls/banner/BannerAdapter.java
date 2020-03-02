package com.zh.commonuilibrary.controls.banner;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zh.commonuilibrary.R;
import com.zh.commonuilibrary.util.DensityUtils;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @describe: Banner rv的adapter
 * @author: Z H
 * @date: 2020/3/2 9:58
 * @pkgName: com.zh.commonuilibrary.controls.banner
 */
public class BannerAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private Context context;
    private XBannerCallback callback;

    private int mPhoneWidth;
    private int sideMargin;  //中间图片带屏幕两边的距离
    private int pageMargin;  //中间图片到两变图片的距离


    public BannerAdapter(Context context, int layoutResId, List<T> data) {
        super(layoutResId, data);
        this.context = context;
        mPhoneWidth = DensityUtils.getPhoneWidth(context);
    }

    public void setMargin(int sideMargin, int pageMargin) {
        this.sideMargin = DensityUtils.dip2px(context, sideMargin);
        this.pageMargin = DensityUtils.dip2px(context, pageMargin);
        notifyDataSetChanged();
    }


    void setXBannerCallback(XBannerCallback callback) {
        this.callback = callback;
    }


    @Override
    protected void convert(BaseViewHolder holder, T t) {
        int position = holder.getPosition();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.width = mPhoneWidth - 2 * sideMargin;
        layoutParams.leftMargin = pageMargin;
        layoutParams.rightMargin = pageMargin;
        holder.itemView.setLayoutParams(layoutParams);
        if (callback != null) {
            callback.convert(holder.itemView, position, t);
        }
    }
}
