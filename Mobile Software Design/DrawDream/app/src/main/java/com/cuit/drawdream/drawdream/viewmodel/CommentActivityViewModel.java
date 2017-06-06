package com.cuit.drawdream.drawdream.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.cuit.drawdream.bean.ReplayEntity;
import com.cuit.drawdream.bean.ReplayEntityDao;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.utils.tool.Config;
import com.cuit.drawdream.drawdream.view.CommentActivity;
import com.kelin.mvvmlight.command.ReplyCommand;

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
    private Handler mHandler;

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
        saveReplay();
        Toast.makeText(mContext,"评论成功",Toast.LENGTH_SHORT)
                .show();
        CommentActivity.instance.finish();
    });

    public void saveReplay(){
        ReplayEntityDao dao = MyApplication.daoSession.getReplayEntityDao();
        dao.insertOrReplace(new ReplayEntity(mId, Config.USER_ID,mContent.get(),"2017/08/06"));
    }


    @Override
    public void destroy() {

    }
}
