package com.kelin.mvvmlight.bindingadapter.button;

import android.graphics.Color;
import android.widget.Button;

/**
 * Created by JWZ on 2016/11/17 0017.
 */

public class ViewBindingAdapter {
    @android.databinding.BindingAdapter(value = {"setButtonTextColor"} , requireAll = true)
    public static void setTextColor(Button button , String textColor){
        button.setTextColor(Color.parseColor(textColor));
    }
}
