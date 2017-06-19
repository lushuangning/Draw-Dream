package com.cuit.drawdream.drawdream.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.databinding.ActivityResultBinding;
import com.cuit.drawdream.drawdream.viewmodel.ResultActivityViewModel;

public class ResultActivity extends BaseActivity {

    private ActivityResultBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mBinding =  DataBindingUtil.setContentView(this, R.layout.activity_result);
        ResultActivityViewModel viewModel = new ResultActivityViewModel(this);
        mBinding.setResultActivityViewModel(viewModel);
    }

    @Override
    protected void destroy() {

    }
}
