package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.MainThread;
import android.util.Log;
import android.widget.Toast;

import com.cuit.drawdream.bean.AccountEntity;
import com.cuit.drawdream.bean.AccountEntityDao;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.bean.response.ResponseLogin;
import com.cuit.drawdream.drawdream.utils.tool.Config;
import com.cuit.drawdream.drawdream.utils.tool.Validator;
import com.cuit.drawdream.drawdream.view.LoginActivity;
import com.cuit.drawdream.drawdream.view.MainActivity;
import com.google.gson.Gson;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created  on 2017/2/15 0015.
 */

public class LoginActivityViewModel extends BaseViewModel{
    private Context mContext;
    private ArrayList<AccountEntity> mList;
    private Subscription mSubscription;

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
                Gson gson = new Gson();
                HashMap<String ,String > paramsMap = new HashMap<>();
                paramsMap.put("account",mUserName.get());
                paramsMap.put("pwd",mUserPassword.get());
                String strGson = gson.toJson(paramsMap);
                Log.i("STRGSON", strGson);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strGson);
                mSubscription = getApplication()
                        .getNetworkService()
                        .login(body)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(getApplication().defaultSubscribeScheduler())
                        .subscribe(new Observer<Response<ResponseLogin>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("Error", "错误" + e.getMessage());
                                Toast.makeText(mContext,"无法连接服务器!",Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onNext(Response<ResponseLogin> responseLoginResponse) {
                                Log.i("RESPONSE",responseLoginResponse.body().getSuccess());
                                if(responseLoginResponse.body().getSuccess().equals("true")){
                                    Config.USER_INFO = responseLoginResponse.body().getData();
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    mContext.startActivity(intent);
                                    LoginActivity.instance.finish();
                                    Toast.makeText(mContext,"登录成功",Toast.LENGTH_SHORT).show();
                                }else{
                                    if(responseLoginResponse.body().getMsg().equals(Config.CODE_NONE)){
                                        Toast.makeText(mContext,"用户不存在，请重新输入！",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(mContext,"用户名或者密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }else{
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
