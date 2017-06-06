package com.cuit.drawdream.drawdream.view;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.databinding.ActivityCommentBinding;
import com.cuit.drawdream.drawdream.viewmodel.CommentActivityViewModel;

/**
 * ClassName : CommentActivity
 * Created by yangq
 * On 2017/6/6.
 * Desc :
 */

public class CommentActivity extends BaseActivity {

    private ActivityCommentBinding mBinding;

    public static Activity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment);
        instance = this;
        Bundle bundle = getIntent().getExtras();
        CommentActivityViewModel viewModel = new CommentActivityViewModel(this,bundle.getString("DetailId"));
        mBinding.pvComment.setTitle("评论");
        mBinding.setCommentViewModel(viewModel);
    }


    @Override
    protected void destroy() {

    }
}
