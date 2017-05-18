package com.cuit.drawdream.drawdream.bean.ordinary;

import com.cuit.drawdream.drawdream.bean.BaseBean;

/**
 * class :    DetialArticleEntity
 * Created by yangq
 * At         2017/5/17.
 * Desc :
 */

public class DetialArticleEntity extends BaseBean {

    private String title;

    private String author;

    private String readNum;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DetialArticleEntity(String title, String author, String readNum, String content) {
        this.title = title;
        this.author = author;
        this.readNum = readNum;
        this.content = content;
    }

    public DetialArticleEntity(String title, String readNum) {
        this.title = title;
        this.readNum = readNum;
    }
}
