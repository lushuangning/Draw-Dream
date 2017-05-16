package com.cuit.drawdream.drawdream.utils.tool;

import java.util.ArrayList;

/**
 * 创建者 sailwish008
 * 创建时间 2016/12/8.
 */

public class ArrayListFunctionUtil {

    /**
     * 删除指定元素
     * @param strings
     * @return
     */
    public static ArrayList<String> reMoveString (ArrayList<String> strings, String string) {
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i) == string) {
                strings.remove(i);
                i--;
            }
        }
        return strings;
    }

    /**
     * arraylist转string
     * @param strings
     * @return
     */
    public static String ListToString (ArrayList<String> strings){
        String result = "";
        for (int i=0;i<strings.size();i++){
            if (i==0){
                result = strings.get(i);
            }
            else {
                result = result+"," +strings.get(i);
            }
        }
        return result;
    }

    /**
     * 数组转arraylist
     * @param strings
     * @return
     */
    public static ArrayList<String> StringsToList (String[] strings){
       ArrayList<String> results = new ArrayList<>();
        for (int i=0; i<strings.length;i++){
            results.add(strings[i]);
        }
        return results;
    }
}
