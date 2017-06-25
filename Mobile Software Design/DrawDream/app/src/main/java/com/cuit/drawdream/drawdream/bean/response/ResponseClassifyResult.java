package com.cuit.drawdream.drawdream.bean.response;

import com.cuit.drawdream.bean.NewsDetail;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sunnylu on 2017/6/25.
 */

public class ResponseClassifyResult {
    @SerializedName("msg")
    private String msg;
    @SerializedName("success")
    private String success;
    @SerializedName("detail")
    private NewsDetail detail;

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

    public NewsDetail getDetail() {
        return detail;
    }

    public void setDetail(NewsDetail detail) {
        this.detail = detail;
    }
}
