package com.kelin.mvvmlight.bindingadapter.swiperefresh;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * Created by kelin on 16-4-26.
 */
public class ViewBindingAdapter {
    @BindingAdapter({"onRefreshCommand"})
    public static void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout, final ReplyCommand onRefreshCommand) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }
            }
        });
    }
    @BindingAdapter({"setColorSchemeResources"})
    public static void setColorSchemeResources(SwipeRefreshLayout swipeRefreshLayout,int [] color) {
        if (color.length==1){
            swipeRefreshLayout.setColorSchemeResources(color[0]);
        }
        if (color.length==2){
            swipeRefreshLayout.setColorSchemeResources(color[0],color[1]);
        }
        if (color.length==3){
            swipeRefreshLayout.setColorSchemeResources(color[0],color[1],color[2]);
        }
        if (color.length==4){
            swipeRefreshLayout.setColorSchemeResources(color[0],color[1],color[2],color[3]);
        }
    }

}
