package com.cuit.drawdream.drawdream.bean.ordinary;

import com.cuit.drawdream.drawdream.bean.BaseBean;

/**
 * class :    ReviewEntity
 * Created by yangq
 * At         2017/5/18.
 * Desc :
 */

public class ReviewEntity extends BaseBean {
    /**
     * 头像
     */
    private String header;
    /**
     * 姓名
     */
    private String name;
    /**
     * 时间
     */
    private String time;
    /**
     * 内容
     */
    private String content;
    /**
     * 评论的对象，用户还是文章,这里是对象的id
     */
    private int toWhoById;
    /**
     * 这里是对象的姓名
     */
    private String toWhoByName;

    public ReviewEntity(){}

    /**
     * 这里只需要姓名就行了，id获取到是为了作为外键使用
     * @param header
     * @param name
     * @param time
     * @param content
     * @param toWhoByName
     */
    public ReviewEntity(String header, String name, String time, String content, String toWhoByName) {
        this.header = header;
        this.name = name;
        this.time = time;
        this.content = content;
        this.toWhoByName = toWhoByName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getToWhoById() {
        return toWhoById;
    }

    public void setToWhoById(int toWhoById) {
        this.toWhoById = toWhoById;
    }

    public String getToWhoByName() {
        return toWhoByName;
    }

    public void setToWhoByName(String toWhoByName) {
        this.toWhoByName = toWhoByName;
    }
}
