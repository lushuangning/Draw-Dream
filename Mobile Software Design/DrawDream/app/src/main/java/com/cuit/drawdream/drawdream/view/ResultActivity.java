package com.cuit.drawdream.drawdream.view;

import android.content.Intent;
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
        Intent intent = getIntent();
        String classify = (String)intent.getExtras().getSerializable("classify");
        mBinding.prTitleResult.setTitle("结果");
        ResultActivityViewModel viewModel = new ResultActivityViewModel(this, classify);
        mBinding.setResultActivityViewModel(viewModel);
    }

    @Override
    protected void destroy() {

    }
}
