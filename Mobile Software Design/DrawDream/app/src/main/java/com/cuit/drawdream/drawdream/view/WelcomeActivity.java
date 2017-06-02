package com.cuit.drawdream.drawdream.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.cuit.drawdream.bean.AccountEntity;
import com.cuit.drawdream.bean.AccountEntityDao;
import com.cuit.drawdream.bean.NewsDetail;
import com.cuit.drawdream.bean.NewsDetailDao;
import com.cuit.drawdream.bean.UserInfoEntity;
import com.cuit.drawdream.bean.UserInfoEntityDao;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.R;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setDataToDataBase();
        new Handler().postDelayed(() -> {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            },3000);
    }



    private void setDataToDataBase() {
        //TODO 此处为本地测试数据
        UserInfoEntityDao userInfoEntityDao = MyApplication.daoSession.getUserInfoEntityDao();
        AccountEntityDao accountEntityDao = MyApplication.daoSession.getAccountEntityDao();
        NewsDetailDao newsDetailDao = MyApplication.daoSession.getNewsDetailDao();
        //创建表格
        userInfoEntityDao.createTable(MyApplication.daoSession.getDatabase(),true);
        accountEntityDao.createTable(MyApplication.daoSession.getDatabase(),true);
        newsDetailDao.createTable(MyApplication.daoSession.getDatabase(),true);
        //测试数据
        userInfoEntityDao.insertOrReplace(new UserInfoEntity("杨庆","1","男","1","1","1"));
        accountEntityDao.insertOrReplace(new AccountEntity("13228189965","1","1"));
        for(int i = 0;i < 12;i++){
            newsDetailDao.insertOrReplace(new NewsDetail("1",
                    "《夏目友人帐》第五季官方图确认公布时间倒计时！",
                    "丢仔",
                    "2017/07/01",
                    "file:///android_asset/test.html",
                    "http://img4.178.com/acg1/201705/289093273635/289093503178.jpg"));
        }
    }

    @Override
    protected void destroy() {

    }
}
