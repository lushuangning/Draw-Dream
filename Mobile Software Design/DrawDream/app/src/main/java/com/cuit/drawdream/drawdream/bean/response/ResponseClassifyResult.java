package com.cuit.drawdream.drawdream.bean.response;

import com.cuit.drawdream.bean.NewsDetail;
import com.cuit.drawdream.drawdream.bean.ordinary.DetailEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunnylu on 2017/6/25.
 */

public class ResponseClassifyResult {
    @SerializedName("msg")
    private String msg;
    @SerializedName("success")
    private String success;
    @SerializedName("data")
    private List<DetailEntity> data;

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

    public List<DetailEntity> getData() {
        return data;
    }

    public void setData(List<DetailEntity> data) {
        this.data = data;
    }
}
