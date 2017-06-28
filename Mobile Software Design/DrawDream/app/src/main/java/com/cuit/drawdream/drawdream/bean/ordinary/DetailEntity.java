package com.cuit.drawdream.drawdream.bean.ordinary;

import com.cuit.drawdream.drawdream.bean.BaseBean;
import com.google.gson.annotations.SerializedName;

/**
 * class :
 *
 * @auther 杨庆
 * data on 2017/6/27.
 * desc :
 */

public class DetailEntity extends BaseBean {
    @SerializedName("nede_cover_img")
    private String nede_cover_img;

    @SerializedName("nede_classify")
    private String nede_classify;

    @SerializedName("nede_title")
    private String nede_title;

    @SerializedName("nede_author")
    private String nede_author;

    @SerializedName("nede_web_time")
    private String nede_web_time;

    @SerializedName("nede_content")
    private String nede_content;

//    @SerializedName("nede_browse")
//    private int nede_browse;
//
//    @SerializedName("nede_love")
//    private int nede_love;

    @SerializedName("pk")
    private String pk;

    public String getNede_cover_img() {
        return nede_cover_img;
    }

    public void setNede_cover_img(String nede_cover_img) {
        this.nede_cover_img = nede_cover_img;
    }

    public String getNede_classify() {
        return nede_classify;
    }

    public void setNede_classify(String nede_classify) {
        this.nede_classify = nede_classify;
    }

    public String getNede_title() {
        return nede_title;
    }

    public void setNede_title(String nede_title) {
        this.nede_title = nede_title;
    }

    public String getNede_author() {
        return nede_author;
    }

    public void setNede_author(String nede_author) {
        this.nede_author = nede_author;
    }

    public String getNede_web_time() {
        return nede_web_time;
    }

    public void setNede_web_time(String nede_web_time) {
        this.nede_web_time = nede_web_time;
    }

    public String getNede_content() {
        return nede_content;
    }

    public void setNede_content(String nede_content) {
        this.nede_content = nede_content;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }
}
