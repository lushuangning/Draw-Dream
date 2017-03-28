package com.kelin.mvvmlight.bindingadapter.linearLayout;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {

    @BindingAdapter({"onSetLinearWidth"})
    public static void onSetWidth(View view, String width) {
        ViewGroup.LayoutParams para = view.getLayoutParams();//获取view的布局
        para.width=Integer.valueOf(width).intValue();//修改宽度
        view.setLayoutParams(para); //设置修改后的布局。
    }

    @BindingAdapter({"onSetLinearBackground"})
    public static void onSetBackground(View view, int baId) {
        view.setBackgroundResource(baId);
    }
}

