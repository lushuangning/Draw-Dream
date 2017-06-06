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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


/**
 * class :    DetailActivity
 * Created by yangq
 * At         2017/5/17.
 * Desc :
 */

public class DetailActivity extends BaseActivity {

    private ActivityDetailBinding mBinding;
    public static DetailActivity instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail);
        Intent intent = getIntent();
        ArrayList<ItemIndexEntity> list = (ArrayList<ItemIndexEntity>)intent.getExtras().getSerializable("Detail");
        ItemIndexEntity entity = list.get(0);
        DetailActivityViewModel viewModel = new DetailActivityViewModel(this,entity);
        mBinding.setDetailActivityViewModel(viewModel);
        mBinding.pvDetail.setTitle("详情");
        mBinding.wvContentDetail.loadUrl(entity.getContent());
    }

    public void finish(){
        finish();
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
