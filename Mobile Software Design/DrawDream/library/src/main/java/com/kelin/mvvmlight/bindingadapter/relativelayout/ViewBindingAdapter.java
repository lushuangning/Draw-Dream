package com.kelin.mvvmlight.bindingadapter.relativelayout;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.kelin.mvvmlight.command.ReplyCommand;
import com.kelin.mvvmlight.command.ResponseCommand;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {

    @BindingAdapter({"onSetWidth"})
    public static void onSetWidth(View view, String width) {
        ViewGroup.LayoutParams para = view.getLayoutParams();//获取view的布局
        para.width=Integer.valueOf(width).intValue();//修改宽度
        view.setLayoutParams(para); //设置修改后的布局。
    }

    @BindingAdapter({"onSetHeight"})
    public static void onSetHeight(View view, String height) {
        ViewGroup.LayoutParams para = view.getLayoutParams();//获取view的布局
        para.height=Integer.valueOf(height).intValue();//修改宽度
        view.setLayoutParams(para); //设置修改后的布局。
    }

    @BindingAdapter({"onSetBackground"})
    public static void onSetBackground(View view, int baId) {
        view.setBackgroundResource(baId);
    }

    @BindingAdapter({"line_color"})
    public static void line_color(View view, String color) {
        view.setBackgroundColor(Color.parseColor(color));
    }
}

