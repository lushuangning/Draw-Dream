package com.cuit.drawdream.drawdream.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.cuit.drawdream.bean.ReplayEntity;
import com.cuit.drawdream.bean.ReplayEntityDao;
import com.cuit.drawdream.bean.UserInfoEntity;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.bean.ordinary.ReviewEntity;
import com.cuit.drawdream.drawdream.bean.response.ResponseInfo;
import com.cuit.drawdream.drawdream.utils.tool.Config;
import com.cuit.drawdream.drawdream.view.CommentActivity;
import com.google.gson.Gson;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * ClassName : CommentActivityViewModel
 * Created by yangq
 * On 2017/6/6.
 * Desc :
 */

public class CommentActivityViewModel extends BaseViewModel {

    private static final String TAG = "commentActivityVM";
    private Context mContext;
    private String mId;
    private Handler mHandler = new Handler();
    private Subscription mSubscription;

    public final ObservableField<String > mContent = new ObservableField<>();

    public CommentActivityViewModel(Context context,String id) {
        super(context);
        mContext = context;
        mId = id;
    }

    /**
     * 提交
     */
    public final ReplyCommand handOn = new ReplyCommand(()->{
        Log.d(TAG," 提交成功！" + mContent.get());
//        saveReplay();
        if(null != mContent.get()){
            sendDataToNet();
        }else{
            Toast.makeText(mContext,"内容不能为空！",Toast.LENGTH_SHORT).show();
        }

    });

    /**
     * 将评论发送至服务器
     */
    private void sendDataToNet() {
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id",Config.USER_INFO.getUserId());
        map.put("news_id",mId);
        map.put("comment_content",mContent.get());
        String jsonStr = gson.toJson(map);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),jsonStr);
        mSubscription = getApplication()
                .getNetworkService()
                .comment(body)
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
                            mHandler = MyApplication.getInstance().getHandler();
                            Message msg = new Message();
                            msg.arg1 = 1;
                            //TODO msg what传递新增的数据
                            ReviewEntity entity = new ReviewEntity();
                            entity.setContent(mContent.get());
                            entity.setTime("2017/08/06");
                            entity.setName(Config.USER_INFO.getUserName());
                            entity.setHeader("file:///android_asset/head1.jpg");
                            msg.obj = entity;
                            mHandler.sendMessage(msg);
                            CommentActivity.instance.finish();
                        }
                    }
                });
    }

//    public void saveReplay(){
//        ReplayEntityDao dao = MyApplication.daoSession.getReplayEntityDao();
//        dao.insertOrReplace(new ReplayEntity(mId, Config.USER_ID,mContent.get(),"2017/08/06"));
//    }


    @Override
    public void destroy() {

    }
}
