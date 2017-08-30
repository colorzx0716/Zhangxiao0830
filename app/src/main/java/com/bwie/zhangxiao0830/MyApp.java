package com.bwie.zhangxiao0830;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by 张肖肖 on 2017/8/30.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initIMG();
        x.Ext.init(this);
        x.Ext.setDebug(false);

    }

    private void initIMG() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
               .build();

        ImageLoaderConfiguration cofig = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(cofig);

    }
}
