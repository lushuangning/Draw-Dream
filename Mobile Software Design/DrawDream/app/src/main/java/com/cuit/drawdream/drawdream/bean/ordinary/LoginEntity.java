package com.cuit.drawdream.drawdream.bean.ordinary;

import com.cuit.drawdream.drawdream.bean.BaseBean;
import com.google.gson.annotations.SerializedName;

/**
 * Created by double on 2017/6/28.
 */

public class LoginEntity  extends BaseBean {
    @SerializedName("user_id")
    private String user_id;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("user_gender")
    private String user_gender;

    @SerializedName("user_phone")
    private String user_phone;

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("user_sign")
    private String user_sign;
}
