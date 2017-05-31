package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.cuit.drawdream.bean.AccountEntity;
import com.cuit.drawdream.bean.AccountEntityDao;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.utils.tool.Config;
import com.cuit.drawdream.drawdream.utils.tool.Validator;
import com.cuit.drawdream.drawdream.view.MainActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

/**
 * Created by sailwish008 on 2017/2/15 0015.
 */

public class LoginActivityViewModel extends BaseViewModel{
    private Context mContext;
    private ArrayList<AccountEntity> mList;

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
                isErrorTextShowing.set(false);
                //TODO 这里发起登录请求,2.0版本中这里应该请求网络
                //test
                if(checkAccount(mUserName.get(),mUserPassword.get())){
                    Intent intent = new Intent(mContext,MainActivity.class);
                    mContext.startActivity(intent);
                }else {
                    Toast.makeText(mContext,"账号密码有错误，请重新输入！",Toast.LENGTH_SHORT).show();
                }

            }else{
                //TODO 这里书写格式错误的处理代码,抖动效果未加
                isErrorTextShowing.set(true);
            }
        }else{
            Toast.makeText(mContext,"请输入账号或者密码",Toast.LENGTH_SHORT).show();
        }
    });

    /**
     *  查询数据库，判断账号密码是否正确
     * @param account
     * @param pwd
     * @return
     */
    private boolean checkAccount(String account ,String pwd){
        AccountEntityDao dao = MyApplication.daoSession.getAccountEntityDao();
        mList = (ArrayList<AccountEntity>) dao.loadAll();
        for(AccountEntity entity :mList){
            if(entity.getAccount().equals(account) &&
                    entity.getPwd().equals(pwd)){
                Config.USER_ID = entity.getUser_id();
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
