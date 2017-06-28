package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.cuit.drawdream.drawdream.view.ResultActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * class :SearchFragmentViewModel
 *
 * @auther 杨庆
 * data on 2017/6/28.
 * desc :
 */

public class SearchFragmentViewModel extends BaseViewModel {

    public final ObservableField<String > mSearchContent = new ObservableField<>();

    public SearchFragmentViewModel(Context context) {
        super(context);
//        mSearchContent.set("请输入搜索名称");
    }

    @Override
    public void destroy() {

    }

    /**
     * 搜索
     */
    public final ReplyCommand search = new ReplyCommand(()->{
        Bundle bundle = new Bundle();
        bundle.putString("classify", mSearchContent.get());
        Intent intent = new Intent(mContext,ResultActivity.class);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    });
    /**
     * 取消
     */
    public final ReplyCommand cancal = new ReplyCommand(()->{
        //点击取消后，搜索框置空
        mSearchContent.set("");
    });
}
