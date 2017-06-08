package com.cuit.drawdream.drawdream.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.view.adapter.MainVpAdapter;
import com.cuit.drawdream.drawdream.view.fragment.ClassifyFragment;
import com.cuit.drawdream.drawdream.view.fragment.IndexFragment;
import com.cuit.drawdream.drawdream.view.fragment.MyInfoFragment;
import com.cuit.drawdream.drawdream.view.fragment.SectionFragment;


import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * class :    MainActivity
 * Created by yangq
 * At         2017/3/28.
 * Desc :
 */

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private static boolean isExit = false;

    private ViewPager mViewPager;
    private MainVpAdapter mMainVpAdapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        //初始化viewPager
        mViewPager = (ViewPager)findViewById(R.id.vp_index);
        List<Fragment> fragments = new ArrayList<>();

        //TODO 这里添加需要的fragment
        fragments.add(new IndexFragment());
        fragments.add(new SectionFragment());
        fragments.add(new IndexFragment());
        fragments.add(new MyInfoFragment());

        mMainVpAdapter = new MainVpAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mMainVpAdapter);

        //初始化navigationTabBar
//        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final NavigationTabBar navigationTabBar = (NavigationTabBar)findViewById(R.id.ntb_index);
        final ArrayList<NavigationTabBar.Model> model = new ArrayList<NavigationTabBar.Model>();
        model.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_index),
                Color.parseColor("#ffffff"))
                .title("首页")
                .badgeTitle("NTB")
                .build()
        );
        model.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_classify),
                Color.parseColor("#ffffff"))
                .title("分类")
                .badgeTitle("NTB")
                .build()
        );
        model.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_search),
                Color.parseColor("#ffffff"))
                .title("搜索")
                .badgeTitle("NTB")
                .build()
        );
        model.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_me),
                Color.parseColor("#ffffff"))
                .title("个人")
                .badgeTitle("NTB")
                .build()
        );
        navigationTabBar.setModels(model);
        navigationTabBar.setViewPager(mViewPager,0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出程序判断
     */
    private void exit() {
        if(!isExit){
            isExit = true;
            Toast.makeText(this,"请再按一次退出程序!",Toast.LENGTH_LONG)
                    .show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void destroy() {

    }
}
