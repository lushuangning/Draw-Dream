package com.cuit.drawdream.drawdream.bean.ordinary;

import com.cuit.drawdream.drawdream.bean.BaseBean;
import com.google.gson.annotations.SerializedName;

/**
 * class :
 *
 * @auther 杨庆
 * data on 2017/6/29.
 * desc :
 */

public class UserEntity extends BaseBean {
    @SerializedName("user_name")
    private String userName;

    @SerializedName("user_email")
    private String userEmail;

    @SerializedName("user_gender")
    private String userGender;

    @SerializedName("user_phone")
    private String userPhone;

    @SerializedName("user_sign")
    private String userSign;

    @SerializedName("user_id")
    private String userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
