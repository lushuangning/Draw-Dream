package com.kelin.mvvmlight.bindingadapter.banner;

import com.kelin.mvvmlight.command.ReplyCommand;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * class :    ViewBindingAdapter
 * Created by yangq
 * At         2017/5/17.
 * Desc :
 */

public class ViewBindingAdapter {

    @android.databinding.BindingAdapter(value = {"setImageLoader"} , requireAll = true)
    public static void setImageLoader(Banner banner , ImageLoader imageLoader){
        banner.setImageLoader(imageLoader);
    }

    @android.databinding.BindingAdapter(value = {"setImages"} , requireAll = true)
    public static void setImages(Banner banner , ArrayList<String> images){
        banner.setImages(images);
    }

    @android.databinding.BindingAdapter(value = {"isOk"} ,requireAll = true)
    public static void isOk(Banner banner ,Boolean isOk){
        if(isOk){
            banner.start();
        }
    }

    @android.databinding.BindingAdapter(value = {"listener"} ,requireAll = true)
    public static void listener(Banner banner, final ReplyCommand replyCommand){
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(null != replyCommand ){
                    replyCommand.execute(position);
                }
            }
        });
    }
}
