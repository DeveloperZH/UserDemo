package com.zh.commonuilibrary.controls.banner;

import android.view.View;

/**
 * @describe: XBanner的回调
 * @author: Z H
 * @date: 2020/3/2 9:41
 * @pkgName: com.zh.commonuilibrary.controls.banner
 */
public interface XBannerCallback<T> {

    void convert(View itemView, int position, T t);
}
