package com.cuit.drawdream.drawdream.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.cuit.drawdream.bean.AccountEntity;
import com.cuit.drawdream.bean.AccountEntityDao;
import com.cuit.drawdream.bean.ClassifyDetailEntity;
import com.cuit.drawdream.bean.ClassifyDetailEntityDao;
import com.cuit.drawdream.bean.NewsDetail;
import com.cuit.drawdream.bean.NewsDetailDao;
import com.cuit.drawdream.bean.ReplayEntity;
import com.cuit.drawdream.bean.ReplayEntityDao;
import com.cuit.drawdream.bean.UserInfoEntity;
import com.cuit.drawdream.bean.UserInfoEntityDao;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.R;

public class WelcomeActivity extends BaseActivity {

    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initData();
        setDataToDataBase();
        new Handler().postDelayed(() -> {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            },3000);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mPref = getSharedPreferences("AppStatus",0);
    }


    private void setDataToDataBase() {
        if(mPref.getBoolean("isFirstRun",true)){
            //TODO 此处为本地测试数据
            UserInfoEntityDao userInfoEntityDao = MyApplication.daoSession.getUserInfoEntityDao();
            AccountEntityDao accountEntityDao = MyApplication.daoSession.getAccountEntityDao();
            NewsDetailDao newsDetailDao = MyApplication.daoSession.getNewsDetailDao();
            ClassifyDetailEntityDao classifyDetailEntityDao = MyApplication.daoSession.getClassifyDetailEntityDao();
            ReplayEntityDao replayEntityDao = MyApplication.daoSession.getReplayEntityDao();
            //创建表格
            userInfoEntityDao.createTable(MyApplication.daoSession.getDatabase(),true);
            accountEntityDao.createTable(MyApplication.daoSession.getDatabase(),true);
            newsDetailDao.createTable(MyApplication.daoSession.getDatabase(),true);
            replayEntityDao.createTable(MyApplication.daoSession.getDatabase(),true);
            //测试数据
            userInfoEntityDao.insertOrReplace(new UserInfoEntity(new Long(0),"杨庆","1","男","1","1","1"));
            userInfoEntityDao.insertOrReplace(new UserInfoEntity(new Long(1),"双儿","2","男","1","1","1"));
            userInfoEntityDao.insertOrReplace(new UserInfoEntity(new Long(2),"羊羊","3","男","1","1","1"));
            accountEntityDao.insertOrReplace(new AccountEntity("13228189965","1",new Long(0)));
            accountEntityDao.insertOrReplace(new AccountEntity("18702807538","1",new Long(1)));

            classifyDetailEntityDao.insertOrReplace(
                    new ClassifyDetailEntity(new Long(0),
                            "热血",
                            "http://www.shuangfile.site/images/android_app_res/emoticons-color_devil.png"));

            classifyDetailEntityDao.insertOrReplace(
                    new ClassifyDetailEntity(new Long(1),
                            "恐怖",
                            "http://www.shuangfile.site/images/android_app_res/objects-color_skull.png"));

            for(int i = 0;i < 3;i++){
                newsDetailDao.insertOrReplace(new NewsDetail("1",
                        "「狐妖小红娘」日语版追加CAST公布，国语版「南国篇」确定制作",
                        "双儿",
                        "2017/07/01",
                        "file:///android_asset/text2.html",
                        "http://img4.178.com/acg1/201706/290464239679/290468626785.jpg"));
                newsDetailDao.insertOrReplace(new NewsDetail("2",
                        "《夏目友人帐》第五季官方图确认公布时间倒计时！",
                        "羊羊",
                        "2017/07/01",
                        "file:///android_asset/test.html",
                        "http://img4.178.com/acg1/201705/289093273635/289093503178.jpg"));
                newsDetailDao.insertOrReplace(new NewsDetail("3",
                        "魔幻史诗《幻镜诺德琳》上线，很久没有看到这么良心的国产动画了",
                        "杨庆",
                        "2017/07/01",
                        "file:///android_asset/text4.html",
                        "http://img3.178.com/acg1/201705/289187653326/289187898340.jpg"));
                newsDetailDao.insertOrReplace(new NewsDetail("4",
                        "非遗+动漫有多惊艳？狐妖和灵剑山有新画风了！",
                        "小李子",
                        "2017/07/01",
                        "file:///android_asset/text3.html",
                        "http://img4.178.com/acg1/201705/290222779036/290222904405.png"));
            }

            for(int i = 0;i < 4;i++){
                replayEntityDao.insertOrReplace(new ReplayEntity("1", new Long(0),"小红娘更新了，小红娘更行了！","2017/06/05"));
                replayEntityDao.insertOrReplace(new ReplayEntity("3", new Long(1),"诺德林好好看，诺德林好好看！","2017/06/05"));
                replayEntityDao.insertOrReplace(new ReplayEntity("4", new Long(2),"狐妖狐妖，狐妖狐妖！","2017/06/05"));
                replayEntityDao.insertOrReplace(new ReplayEntity("1", new Long(1),"诺德林好好看，诺德林好好看！","2017/06/05"));
                replayEntityDao.insertOrReplace(new ReplayEntity("1", new Long(2),"狐妖狐妖，狐妖狐妖！","2017/06/05"));
            }
            mPref.edit()
                    .putBoolean("isFirstRun",false)
                    .commit();
        }
    }

    @Override
    protected void destroy() {

    }
}
