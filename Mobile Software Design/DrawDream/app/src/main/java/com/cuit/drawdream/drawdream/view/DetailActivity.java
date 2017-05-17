package com.cuit.drawdream.drawdream.view;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.DetialArticleEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * class :    DetailActivity
 * Created by yangq
 * At         2017/5/17.
 * Desc :
 */

public class DetailActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initData(jSoup());
    }

    private void initData(DetialArticleEntity entity) {
    }

    private DetialArticleEntity jSoup() {

        DetialArticleEntity entity = new DetialArticleEntity();
        String title = "";
        Runnable run = new Runnable() {
            @Override
            public void run() {
                String errorStr = "";
                String title = "";
                try{
                    Document doc = Jsoup.connect("http://acg.178.com/201705/289002212898.html").get();
                    Elements elements = doc.select("div.artical-content");
                    title = elements.select("p").first().text();
                    title = title.replaceAll("<.*>.*</[\\w-\\W-]*>","");
                    Log.d("elements:",title);
                }catch (Exception e){
                    errorStr = e.getMessage();
                    e.printStackTrace();
                }
            }
        };
        new Thread(run).start();

        return entity;
    }

    @Override
    protected void destroy() {

    }
}
