package com.cuit.drawdream.drawdream.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.DetialArticleEntity;
import com.cuit.drawdream.drawdream.bean.ordinary.ItemIndexEntity;
import com.cuit.drawdream.drawdream.databinding.ActivityDetailBinding;
import com.cuit.drawdream.drawdream.viewmodel.DetailActivityViewModel;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.RequestBody;
import rx.Subscription;


/**
 * class :    DetailActivity
 * Created by yangq
 * At         2017/5/17.
 * Desc :
 */

public class DetailActivity extends BaseActivity {

    private ActivityDetailBinding mBinding;
    public static DetailActivity instance;

    private ItemIndexEntity mEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail);
        Intent intent = getIntent();
        ArrayList<ItemIndexEntity> list = (ArrayList<ItemIndexEntity>)intent.getExtras().getSerializable("Detail");
        mEntity = list.get(0);
        initUI();
    }

    private void initUI(){
        DetailActivityViewModel viewModel = new DetailActivityViewModel(this,mEntity);
        mBinding.setDetailActivityViewModel(viewModel);
        mBinding.pvDetail.setTitle("详情");
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
//            mBinding.wvContentDetail.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }
//        mBinding.wvContentDetail.getSettings().setBlockNetworkImage(false);
        mBinding.wvContentDetail.loadUrl(mEntity.getContent());
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
