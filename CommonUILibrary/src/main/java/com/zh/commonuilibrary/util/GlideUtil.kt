package com.zh.commonuilibrary.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule

/**
 * @describe: Glide的辅助类 4.x版本必须有
 *  这里之所以没有用Object直接声明类 是因为 GeneratedAppGlideModuleImpl中new了AppGlideModule的子类
 * @author: Z H
 * @date: 2020/3/2 16:08
 * @pkgName: com.zh.commonuilibrary.util
 */
@GlideModule
class GlideUtil : AppGlideModule() {

    companion object {

        /**
         * 加载圆形图片
         * */
        fun setCircleImg(context: Context, imageUrl: String, view: ImageView) {
            GlideApp.with(context).load(imageUrl)
                    .transform(CircleCrop())
                    .into(view)
        }

        /**
         * 加载圆形图片
         * */
        fun setCircleImg(context: Context, imageUrl: String, view: ImageView, placeImg: Int, errorImg: Int) {
            GlideApp.with(context).load(imageUrl)
                    .transform(CircleCrop())
                    .placeholder(placeImg)
                    .error(errorImg)
                    .into(view)
        }

        /**
         * 加载圆角图片
         * */
        fun setRoundedCornerImg(context: Context,imageUrl:String,view: ImageView,roundingRadius:Int){
            GlideApp.with(view).load(imageUrl)
                    .transform(RoundedCorners(roundingRadius))
                    .into(view)
        }

        /**
         * 加载圆角图片
         * */
        fun setRoundedCornerImg(context: Context,imageUrl:String,view: ImageView,roundingRadius:Int, placeImg: Int, errorImg: Int){
            GlideApp.with(view).load(imageUrl)
                    .transform(RoundedCorners(roundingRadius))
                    .placeholder(placeImg)
                    .error(errorImg)
                    .into(view)
        }
    }

}