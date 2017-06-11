package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import com.cuit.drawdream.bean.UserInfoEntity;
import com.cuit.drawdream.drawdream.utils.tool.Config;
import com.kelin.mvvmlight.command.ReplyCommand;


/**
 * ClassName : MyInfoMgtActivityViewModel
 * Created by yangq
 * On 2017/6/11.
 * Desc :
 */

public class MyInfoMgtActivityViewModel extends BaseViewModel {

    private Context mContext;
    private UserInfoEntity mEntity;

    public final ObservableBoolean isMan = new ObservableBoolean(true);
    public final ObservableBoolean isWoman = new ObservableBoolean(false);
    public final ObservableField<String > mAccount = new ObservableField<>();
    public final ObservableField<String > mName = new ObservableField<>();
    public final ObservableField<String > mSign = new ObservableField<>();
    public final ObservableField<String > mEmail = new ObservableField<>();


    public MyInfoMgtActivityViewModel(Context context, UserInfoEntity entity) {
        super(context);
        mContext = context;
        mEntity = entity;
        if(null != mEntity){
            initUI();
        }
    }

    private void initUI() {
        mAccount.set(mEntity.getUser_id());
        mName.set(mEntity.getUser_name());
        mSign.set(mEntity.getUser_sign());
        mEmail.set(mEntity.getUser_email());
        if(mEntity.getUser_gander().equals("女")){
            isMan.set(false);
            isWoman.set(true);
        }
    }

    /**
     * 提交修改的信息
     */
    public final ReplyCommand handIn = new ReplyCommand(()->{
//        if(isMan.get()){
//            Log.d("The gander is ","man");
//        }else if(isWoman.get()){
//            Log.d("The gander is ","woman");
//        }else {
//            Log.d("The gander is ","null");
//        }

        //TODO 这里提交时将config.NAME设置好

    });

    @Override
    public void destroy() {

    }
}
