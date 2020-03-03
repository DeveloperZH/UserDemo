package com.zh.userdemo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zh.commonuilibrary.controls.banner.BannerAdapter;
import com.zh.commonuilibrary.controls.banner.XBannerCallback;
import com.zh.commonuilibrary.controls.banner.XBannerView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.PagerSnapHelper;

/**
 * @describe: XBannerView
 * @author: Z H
 * @date: 2020/2/27 18:41
 * @pkgName: com.zh.userdemo
 */
public class XBannerViewActivity extends BaseActivity {

    private XBannerView xBanner,xtv_banner_1;
    private List<String> dataList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_xbanner;
    }

    @Override
    public void initView() {
        xBanner = findViewById(R.id.xtv_banner);
        xtv_banner_1 = findViewById(R.id.xtv_banner_1);
        dataList.add("http://cos.clife.net/31423/f42c8a72b4def37b7602948d6a93e74a.jpg");
        dataList.add("http://cos.clife.net/31423/5aea376eba72aa91f543173f74ac5595.jpg");
        dataList.add("http://cos.clife.net/31423/7c78da3ca478d2fca285a66d42581a47.jpg");
        dataList.add("http://cos.clife.net/31423/f42c8a72b4def37b7602948d6a93e74a.jpg");
        dataList.add("http://cos.clife.net/31423/5aea376eba72aa91f543173f74ac5595.jpg");
        dataList.add("http://cos.clife.net/31423/7c78da3ca478d2fca285a66d42581a47.jpg");
    }

    @Override
    public void initData() {
        /**
         * 如果没有特殊的要求  推荐使用setData（）方法  不用去关注Adapter的实现
         * */
        xBanner.setData(dataList, R.layout.item_banner, new XBannerCallback() {
            @Override
            public void convert(View itemView, int position, Object o) {
                ImageView imageView = itemView.findViewById(R.id.iv_item);
                Glide.with(XBannerViewActivity.this).load(dataList.get(position))
                        .error(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .into(imageView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(XBannerViewActivity.this, "position>>" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        xBanner.setMargin(80, 20);
        xBanner.setSnapHelper(new PagerSnapHelper());
        /**
         * 如果想自定义Adapter  则调用setAdapter（） 必须继承BannerAdapter
         * 此方法和setData 二选一即可
         * */
        xtv_banner_1.setAdapter(new TestAdapter(this, R.layout.item_banner, dataList));




    }

    class TestAdapter extends BannerAdapter {

        public TestAdapter(Context context, int layoutResId, List data) {
            super(context, layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, Object o) {
            super.convert(holder, o);
            Log.e("convert>>", "0>>>" + o);
            View itemView = holder.itemView;
            ImageView imageView = itemView.findViewById(R.id.iv_item);
            Glide.with(XBannerViewActivity.this).load((String) o)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(XBannerViewActivity.this, "position>>" + holder.getPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
