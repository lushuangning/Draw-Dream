package com.cuit.drawdream.drawdream.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.drawdream.bean.UserInfoEntity;
import com.cuit.drawdream.bean.UserInfoEntityDao;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.databinding.FragmentMyInfoBinding;
import com.cuit.drawdream.drawdream.utils.tool.Config;
import com.cuit.drawdream.drawdream.viewmodel.MyInfoFragmentViewModel;

import java.util.ArrayList;

/**
 * class :    MyInfoFragment
 * Created by yangq
 * At         2017/5/23.
 * Desc :
 */

public class MyInfoFragment extends Fragment {

    private FragmentMyInfoBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_my_info,container,false);
        MyInfoFragmentViewModel viewModel = new MyInfoFragmentViewModel(getContext());
        mBinding.setMyInfoFragmentViewModel(viewModel);
        return mBinding.getRoot();
    }


}
