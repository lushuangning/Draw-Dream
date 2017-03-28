package com.kelin.mvvmlight.bindingadapter.checkbox;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {


    @android.databinding.BindingAdapter(value = {"OnCheckedChangeListener"}, requireAll = false)
    public static void OnCheckedChangeListener(CheckBox checkBox, final ReplyCommand<Boolean> OnCheckedChangeListener) {

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
               if (OnCheckedChangeListener!= null){
                   OnCheckedChangeListener.execute(isChecked);
               }
            }
        });
    }
}

