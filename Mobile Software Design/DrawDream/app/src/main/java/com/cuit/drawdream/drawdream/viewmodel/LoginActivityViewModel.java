package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.cuit.drawdream.drawdream.utils.tool.Validator;
import com.cuit.drawdream.drawdream.view.MainActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * Created by sailwish008 on 2017/2/15 0015.
 */

public class LoginActivityViewModel extends BaseViewModel{
    private Context mContext;

    public final ObservableField<String > mUserName = new ObservableField<>();
    public final ObservableField<String > mUserPassword = new ObservableField<>();
    public final ObservableBoolean isErrorTextShowing = new ObservableBoolean(false);

    public LoginActivityViewModel(Context context){
        super(context);
        this.mContext = context;
    }

    /**
     * 登录按钮
     */
    public final ReplyCommand login = new ReplyCommand(()->{

        if(null != mUserName.get() && null != mUserPassword.get()){
            if(Validator.isMobile(mUserName.get())){
                //TODO 这里发起登录请求
                //test
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);
            }else{
                //TODO 这里书写格式错误的处理代码,抖动效果未加
                isErrorTextShowing.set(true);
            }
        }else{
            Toast.makeText(mContext,"请输入账号或者密码",Toast.LENGTH_SHORT).show();
        }
    });
    @Override
    public void destroy() {

    }
}
