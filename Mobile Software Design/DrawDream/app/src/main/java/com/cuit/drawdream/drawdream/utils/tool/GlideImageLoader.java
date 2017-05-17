package com.cuit.drawdream.drawdream.utils.tool;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * class :    GlideImageLoader
 * Created by yangq
 * At         2017/5/15.
 * Desc :    用于加载图片
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .into(imageView);
    }
}
