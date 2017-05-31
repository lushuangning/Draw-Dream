package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.cuit.drawdream.bean.UserInfoEntity;
import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * class :    MyInfoFragmentViewModel
 * Created by yangq
 * At         2017/5/31.
 * Desc :
 */

public class MyInfoFragmentViewModel extends BaseViewModel {

    private Context mContext;

    public final ObservableField<String > mName = new ObservableField<>();

    public MyInfoFragmentViewModel(Context context, UserInfoEntity entity) {
        super(context);
        mContext = context;
        mName.set(entity.getUser_name());
    }

    public final ReplyCommand toMyMsg = new ReplyCommand(()->{
        //跳转到msg
        Toast.makeText(mContext,"尚未完成",Toast.LENGTH_SHORT)
                .show();
    });

    @Override
    public void destroy() {

    }
}
