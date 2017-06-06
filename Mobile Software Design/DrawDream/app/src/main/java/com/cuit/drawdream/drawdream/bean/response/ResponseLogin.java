package com.cuit.drawdream.drawdream.bean.response;

import com.cuit.drawdream.bean.UserInfoEntity;
import com.google.gson.annotations.SerializedName;

/**
 * class :    ResponseLogin
 * Created by yangq
 * At         2017/6/6.
 * Desc :
 */

public class ResponseLogin {
    @SerializedName("msg")
    private String msg;
    @SerializedName("success")
    private String success;
    @SerializedName("data")
    private UserInfoEntity data;

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

    public UserInfoEntity getData() {
        return data;
    }

    public void setData(UserInfoEntity data) {
        this.data = data;
    }
}
