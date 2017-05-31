package com.cuit.drawdream.drawdream.bean;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class Video {
    private int img;
    private String name;

    public Video(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
