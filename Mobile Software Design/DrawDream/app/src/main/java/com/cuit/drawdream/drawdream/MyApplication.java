package com.cuit.drawdream.drawdream;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.cuit.drawdream.bean.DaoMaster;
import com.cuit.drawdream.bean.DaoSession;
import com.cuit.drawdream.drawdream.model.service.ServiceFactory;
import com.cuit.drawdream.drawdream.model.service.SwNetworkService;
import com.cuit.drawdream.drawdream.utils.Utils;
import com.cuit.drawdream.drawdream.utils.tool.NetworkManager;
import com.cuit.drawdream.drawdream.utils.widget.CrashHandler;
import com.liulishuo.filedownloader.FileDownloader;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import io.yunba.android.manager.YunBaManager;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by hus on 9/2/2016.
 * 初始化全局用到的变量和服务
 */
public class MyApplication extends Application {
    private String TAG ="云巴推送";
    private SwNetworkService mNetworkService;
    private Scheduler defaultSubscribeScheduler;
    private static String token = "";

    private static MyApplication application;

    public static DaoMaster daoMaster;
    public static DaoSession daoSession;
    public static SQLiteDatabase db;
    public static DaoMaster.DevOpenHelper helper;
    /**
     * 数据库名
     */
    public static final String DB_NAME = "dbnewworld.db";
    public static SharedPreferences mPref;
    @Override
    public void onCreate() {
        super.onCreate();
        if (!NetworkManager.isNetworkConnected(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "网络无法连接！", Toast.LENGTH_LONG).show();
            return;
        }
        setYunBa();
        application = this;
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(this);
        Utils.init(this);
        helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        FileDownloader.init(getApplicationContext());
    }

    public static MyApplication getInstance() {
        return application;
    }

    public SwNetworkService getNetworkService() {
        if (null == mNetworkService) {
            mNetworkService = ServiceFactory.getNetworkService();
        }
        return mNetworkService;
    }

    public void updateNetWorkService() {
        mNetworkService = ServiceFactory.getNetworkService();
    }

    public Scheduler defaultSubscribeScheduler() {
        if (null == defaultSubscribeScheduler) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    public static String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 云巴推送配置
     */
    private void setYunBa(){
        YunBaManager.start(getApplicationContext());
        YunBaManager.subscribe(getApplicationContext(), new String[]{"news"}, new IMqttActionListener() {

            @Override
            public void onSuccess(IMqttToken arg0) {
                Log.d(TAG, "Subscribe topic succeed");
            }

            @Override
            public void onFailure(IMqttToken arg0, Throwable arg1) {
                Log.d(TAG, "Subscribe topic failed");
            }
        });
    }
}
