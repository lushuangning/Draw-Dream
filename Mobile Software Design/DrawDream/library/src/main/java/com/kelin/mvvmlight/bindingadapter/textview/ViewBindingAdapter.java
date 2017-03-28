package com.kelin.mvvmlight.bindingadapter.textview;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.widget.TextView;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {

    @BindingAdapter({"color"})
    public static void setTextColor(TextView textView, String color) {
        textView.setTextColor(Color.parseColor(color));
    }
}

