package com.cuit.drawdream.drawdream.bean.ordinary;

import com.cuit.drawdream.drawdream.bean.BaseBean;

/**
 * Created by sunnylu on 2017/6/19.
 */

public class SearchResultEntity extends BaseBean{

    private Long table_id;

    private String id;

    private String img;

    private String title;

    private String author;

    private String time;

    //浏览次数
    private Integer browse;

    private String content;

    public Long getTable_id() {
        return table_id;
    }

    public void setTable_id(Long table_id) {
        this.table_id = table_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
