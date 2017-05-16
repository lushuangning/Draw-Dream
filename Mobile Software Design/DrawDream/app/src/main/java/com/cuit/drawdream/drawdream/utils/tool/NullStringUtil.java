package com.cuit.drawdream.drawdream.utils.tool;

import android.databinding.ObservableField;

/**
 * 创建者 sailwish008
 * 创建时间 2016/12/9.
 * 数据操作工作类
 */

public class NullStringUtil {

    /**
     * 判断数据是否为null，并且赋值
     * @param temp
     * @param data
     */
    public static void isNULL(ObservableField<String> temp, Object data, int length){
        if (data==null){
            temp.set("暂无");
        }
        else {
            String dataString = (String) data;
            if (dataString.length()>length){
                temp.set(dataString.substring(0,length)+"……");
            }
            else {
                temp.set(dataString);
            }
        }
    }
}
