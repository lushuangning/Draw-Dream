package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.cuit.drawdream.bean.UserInfoEntity;
import com.cuit.drawdream.drawdream.bean.ordinary.UserEntity;
import com.cuit.drawdream.drawdream.bean.response.ResponseInfo;
import com.cuit.drawdream.drawdream.utils.tool.Config;
import com.cuit.drawdream.drawdream.view.MyInfoMgtActivity;
import com.google.gson.Gson;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


/**
 * ClassName : MyInfoMgtActivityViewModel
 * On 2017/6/11.
 * Desc :
 */

public class MyInfoMgtActivityViewModel extends BaseViewModel {

    private static final String TAG = "MyInfoMgtVM";

    private Context mContext;

    private Subscription mSubscription;


    public final ObservableBoolean isMan = new ObservableBoolean(true);
    public final ObservableBoolean isWoman = new ObservableBoolean(false);
    public final ObservableField<String > mAccount = new ObservableField<>();
    public final ObservableField<String > mName = new ObservableField<>();
    public final ObservableField<String > mSign = new ObservableField<>();
    public final ObservableField<String > mEmail = new ObservableField<>();


    public MyInfoMgtActivityViewModel(Context context) {
        super(context);
        mContext = context;
        if(null != Config.USER_INFO.getUserId()){
            initUI();
        }
    }

    /**
     * 给界面设置数据
     */
    private void initUI() {
        //phone作为账号使用
        mAccount.set(Config.USER_INFO.getUserPhone());
        mName.set(Config.USER_INFO.getUserName());
        mSign.set(Config.USER_INFO.getUserSign());
        mEmail.set(Config.USER_INFO.getUserEmail());
        //女性为true，男性为false
        if(Config.USER_INFO.getUserGender().equals("true")){
            isMan.set(false);
            isWoman.set(true);
        }
    }

    /**
     * 提交修改的信息
     */
    public final ReplyCommand handIn = new ReplyCommand(()->{
        if(!mName.get().toString().isEmpty() &&
                !mEmail.get().toString().isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("确认提交么？")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updataToNet();
                        }
                    })
                    .create()
                    .show();

        }

    });

    /**
     * 将新的信息提交到服务器
     */
    private void updataToNet() {
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id",Config.USER_INFO.getUserId());
        map.put("user_name",mName.get());
        map.put("user_email",mEmail.get());
        map.put("user_sign",mSign.get().toString().isEmpty()?"这个人很懒，什么都没留下~":mSign.get());
        map.put("user_gender",isWoman.get()?"true":"false");
        String strGson = gson.toJson(map);
        Log.i("STRGSON", strGson);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strGson);
        mSubscription = getApplication()
                .getNetworkService()
                .userInfo(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(getApplication().defaultSubscribeScheduler())
                .subscribe(new Observer<Response<ResponseInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                        Toast.makeText(mContext,"服务器错误！",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Response<ResponseInfo> responseInfoResponse) {
                        if(responseInfoResponse.body().getSuccess().equals("true")){
                            UserEntity entity = new UserEntity();
                            entity.setUserEmail(mEmail.get());
                            entity.setUserName(mName.get());
                            entity.setUserSign(mSign.get().toString().isEmpty()?"这个人很懒，什么都没有留下":mSign.get());
                            //关闭当前页面
                            MyInfoMgtActivity.instance.finish();
                            Toast.makeText(mContext,"提交成功！",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mContext,"提交失败！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void destroy() {

    }
}
