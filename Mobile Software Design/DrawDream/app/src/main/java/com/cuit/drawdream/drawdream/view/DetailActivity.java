package com.cuit.drawdream.drawdream.view;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.DetialArticleEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
//        initData(jSoup());
        WebView wv = (WebView) findViewById(R.id.wv_content_detail);
        String url = "http://acg.178.com/201705/288980777014.html";

//        wv.loadUrl("file:///android_asset/test.html");
        wv.loadDataWithBaseURL(getHtmlContent(url),null,null,null,url);
    }

    private void initData(DetialArticleEntity entity) {
    }

//    private DetialArticleEntity jSoup() {
//
//        DetialArticleEntity entity = new DetialArticleEntity();
//        String title = "";
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                String errorStr = "";
//                String title = "";
//                try{
//                    Document doc = Jsoup.connect("http://acg.178.com/201705/289002212898.html").get();
//                    Elements elements = doc.select("div.artical-content");
//                    title = elements.select("p").first().text();
//                    Log.d("elements:",title);
//                }catch (Exception e){
//                    errorStr = e.getMessage();
//                    e.printStackTrace();
//                }
//            }
//        };
//        new Thread(run).start();
//
//        return entity;
//    }
    public static String getHtmlContent(String html) {
    //
        Document doc_Dis = Jsoup.parse(html);
        Elements ele_Img = doc_Dis.getElementsByTag("img");
        if (ele_Img.size() != 0) {
            for (Element e_Img : ele_Img) {
                e_Img.attr("style", "max - width:100 %; height:auto;");
            }
        }
    //
        return doc_Dis.toString();
    }

    @Override
    protected void destroy() {

    }
}
