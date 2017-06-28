package com.cuit.drawdream.drawdream.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * class :
 *
 * @auther 杨庆
 * data on 2017/6/29.
 * desc :
 */

public class ResponseInfo {

    @SerializedName("msg")
    private String msg;
    @SerializedName("success")
    private String success;

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
}
