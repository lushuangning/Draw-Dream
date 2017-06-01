package com.cuit.drawdream.drawdream.bean.ordinary;

import com.cuit.drawdream.drawdream.bean.BaseBean;

import java.util.ArrayList;

/**
 * class :    ItemIndexEntity
 * Created by yangq
 * At         2017/5/17.
 * Desc :
 */

public class ItemIndexEntity extends BaseBean {

    private String id;

    private String img;

    private String title;

    private String classify;

    private String author;

    private String time;

    private String content;

    private ArrayList<String > images;

    public ItemIndexEntity(){}

    /**
     * 瀑布流的构造方法
     * @param img
     * @param title
     * @param classify
     * @param author
     * @param time
     */
    public ItemIndexEntity(String img, String title, String classify, String author, String time) {
        this.img = img;
        this.title = title;
        this.classify = classify;
        this.author = author;
        this.time = time;
    }

    /**
     * 轮播图的构造方法
     * @param images
     */
    public ItemIndexEntity(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
