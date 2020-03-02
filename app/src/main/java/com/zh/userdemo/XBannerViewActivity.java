package com.zh.userdemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zh.commonuilibrary.controls.banner.XBannerCallback;
import com.zh.commonuilibrary.controls.banner.XBannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe: XBannerView
 * @author: Z H
 * @date: 2020/2/27 18:41
 * @pkgName: com.zh.userdemo
 */
public class XBannerViewActivity extends BaseActivity {

    private XBannerView xtv_banner;
    private List<String> dataList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_xbanner;
    }

    @Override
    public void initView() {
        xtv_banner = findViewById(R.id.xtv_banner);

        dataList.add("http://cos.clife.net/31423/f42c8a72b4def37b7602948d6a93e74a.jpg");
        dataList.add("http://cos.clife.net/31423/5aea376eba72aa91f543173f74ac5595.jpg");
        dataList.add("http://cos.clife.net/31423/7c78da3ca478d2fca285a66d42581a47.jpg");
        dataList.add("http://cos.clife.net/31423/f42c8a72b4def37b7602948d6a93e74a.jpg");
        dataList.add("http://cos.clife.net/31423/5aea376eba72aa91f543173f74ac5595.jpg");
        dataList.add("http://cos.clife.net/31423/7c78da3ca478d2fca285a66d42581a47.jpg");
    }

    @Override
    public void initData() {
        xtv_banner.setData(dataList, R.layout.item_banner, new XBannerCallback() {
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
                        Toast.makeText(XBannerViewActivity.this,"position>>" + position,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        xtv_banner.setMargin(80,20);
        xtv_banner.setMargin(0,0);
    }
}
