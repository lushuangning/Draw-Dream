package com.cuit.drawdream.drawdream.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cuit.drawdream.bean.UserInfoEntity;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.databinding.ActivityMyInfoMgtBinding;
import com.cuit.drawdream.drawdream.viewmodel.MyInfoMgtActivityViewModel;

import java.util.ArrayList;

/**
 * ClassName : MyInfoMgtActivity
 * Created by yangq
 * On 2017/6/11.
 * Desc :
 */

public class MyInfoMgtActivity extends BaseActivity {

    private ActivityMyInfoMgtBinding mBinding;

    public static MyInfoMgtActivity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_my_info_mgt);
        MyInfoMgtActivityViewModel viewModel = new MyInfoMgtActivityViewModel(this);
        mBinding.pvMyInfo.setTitle("个人资料");
        mBinding.setMgtViewModel(viewModel);
    }

    @Override
    protected void destroy() {

    }
}
