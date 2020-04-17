package com.zh.commonuilibrary.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * @describe: app状态栏辅助类
 * @author: Z H
 * @date: 2020/2/27 18:01
 * @pkgName: com.zh.commonuilibrary.util
 */
public class StateBarHelper {

    private static final String TAG = StateBarHelper.class.getSimpleName();

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
