package com.cuit.drawdream.drawdream.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.utils.tool.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * class :    IndexFragment
 * Created by yangq
 * At         2017/3/28.
 * Desc :
 */

public class IndexFragment extends Fragment {

    private ArrayList<String > images = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false);
        Banner banner = (Banner) view.findViewById(R.id.recycle_view);
        images.add("http://img4.178.com/acg1/201705/288490464121/288490893838.jpg");
        images.add("http://img1.178.com/acg1/201705/288466908147/288471126293.jpg");
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        return view;
    }
}
