package com.cuit.drawdream.drawdream.bean.response;

import com.cuit.drawdream.bean.NewsDetail;
import com.cuit.drawdream.drawdream.bean.BaseBean;
import com.cuit.drawdream.drawdream.bean.ordinary.ReviewEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * class :
 *
 * @auther 杨庆
 * data on 2017/6/26.
 * desc :
 */

public class ResponseReview extends BaseBean {
    @SerializedName("msg")
    private String msg;
    @SerializedName("success")
    private String success;
    @SerializedName("data")
    private ArrayList<ReviewEntity> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<ReviewEntity> getData() {
        return data;
    }

    public void setData(ArrayList<ReviewEntity> data) {
        this.data = data;
    }
}
