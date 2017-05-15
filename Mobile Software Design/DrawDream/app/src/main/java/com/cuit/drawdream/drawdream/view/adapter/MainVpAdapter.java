package com.cuit.drawdream.drawdream.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * class :    MainVpAdapter
 * Created by yangq
 * At         2017/3/28.
 * Desc :
 */

public class MainVpAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public MainVpAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmentList = fragments;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
