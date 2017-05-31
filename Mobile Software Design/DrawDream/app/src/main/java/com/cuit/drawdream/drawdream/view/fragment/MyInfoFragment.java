package com.cuit.drawdream.drawdream.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.drawdream.drawdream.R;

/**
 * class :    MyInfoFragment
 * Created by yangq
 * At         2017/5/23.
 * Desc :
 */

public class MyInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_info,container,false);
        return view;
    }
}
