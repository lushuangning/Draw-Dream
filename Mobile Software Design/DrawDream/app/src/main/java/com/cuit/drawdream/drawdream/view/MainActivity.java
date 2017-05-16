package com.cuit.drawdream.drawdream.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.view.adapter.MainVpAdapter;
import com.cuit.drawdream.drawdream.view.fragment.ClassifyFragment;
import com.cuit.drawdream.drawdream.view.fragment.IndexFragment;

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

    private ViewPager mViewPager;
    private MainVpAdapter mMainVpAdapter;

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
        fragments.add(new ClassifyFragment());
        fragments.add(new IndexFragment());
        fragments.add(new IndexFragment());

        mMainVpAdapter = new MainVpAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mMainVpAdapter);

        //初始化navigationTabBar
//        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final NavigationTabBar navigationTabBar = (NavigationTabBar)findViewById(R.id.ntb_index);
        final ArrayList<NavigationTabBar.Model> model = new ArrayList<NavigationTabBar.Model>();
        model.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_four),
                Color.parseColor("#00000000"))
                .title("首页")
                .badgeTitle("NTB")
                .build()
        );
        model.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_four),
                Color.parseColor("#00000000"))
                .title("分类")
                .badgeTitle("NTB")
                .build()
        );
        model.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_four),
                Color.parseColor("#FFFFFF"))
                .title("搜索")
                .badgeTitle("NTB")
                .build()
        );
        model.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_four),
                Color.parseColor("#FFFFFF"))
                .title("个人")
                .badgeTitle("NTB")
                .build()
        );
        navigationTabBar.setModels(model);
        navigationTabBar.setViewPager(mViewPager,1);
    }

    @Override
    protected void destroy() {

    }
}
