package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.bean.response.ResponseError;
import com.cuit.drawdream.drawdream.utils.ToastUtils;
import com.google.gson.Gson;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by hus on 9/2/2016.
 */
public abstract class BaseViewModel extends BaseObservable {

    private static final String CODE_TOKEN_EXPIRE = "E002";
    protected static Context mContext;
    private static MyApplication mApp;

    protected BaseViewModel(Context context) {
        this.mContext = context;
        mApp = (MyApplication) context.getApplicationContext();
    }

    protected static MyApplication getApplication() {
        if (null == mApp) {
            mApp = (MyApplication) mContext.getApplicationContext();
        }
        return mApp;
    }

    protected boolean checkNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
        if (network != null && network.isConnected()) {
            return true;
        } else {
            ToastUtils.showToastAtBottomOfScreen(mContext, "网络不可用,请检查网络设置");
            return false;
        }
    }

    protected void handleNetworkError(Throwable e) {
        if (e instanceof HttpException) {
            ResponseBody errorResponse = ((HttpException) e).response().errorBody();
            try {
                ResponseError error = new Gson().fromJson(errorResponse.string(), ResponseError.class);
                if (null != error) {
                    ToastUtils.showToastAtCenterOfScreen(mContext, error.getMessage());
                }
                //TODO 如果服务器返回Token过期/登陆失效，自动退出当前界面，进入登录界面
                if (CODE_TOKEN_EXPIRE.equalsIgnoreCase(error.getCode())) {
//                    Intent logoutIntent = new Intent(mContext, LoginActivity.class);
//                    logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    mContext.startActivity(logoutIntent);
//                    ((BaseActivity) mContext).finish();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    /**
     * 程序界面退出，清除资源
     */
    public abstract void destroy();
}
