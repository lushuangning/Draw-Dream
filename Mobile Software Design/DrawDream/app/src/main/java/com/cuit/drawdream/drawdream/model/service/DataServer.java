package com.cuit.drawdream.drawdream.model.service;




import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.MultipleItem;
import com.cuit.drawdream.drawdream.bean.MySection;
import com.cuit.drawdream.drawdream.bean.Status;
import com.cuit.drawdream.drawdream.bean.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class DataServer {

    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";
    private static final String CYM_CHAD = "绘梦工作室";
    private static final String ITEM_RESOURCE_WEB_SERVER = "http://115.159.40.239/images/android_app_res/";

    private DataServer() {
    }

    public static List<Status> getSampleData(int lenth) {
        List<Status> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Status status = new Status();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    public static List<Status> addData(List list, int dataSize) {
        for (int i = 0; i < dataSize; i++) {
            Status status = new Status();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("Powerful and flexible RecyclerAdapter https://github.com/CymChad/BaseRecyclerViewAdapterHelper");
            list.add(status);
        }

        return list;
    }

    public static List<MySection> getSampleData() {
        List<MySection> list = new ArrayList<>();
        list.add(new MySection(true, "漫画", false));
        list.add(new MySection(new Video(R.mipmap.archer, "热血")));
        list.add(new MySection(new Video(R.mipmap.badminton, "恐怖")));
        list.add(new MySection(new Video(R.mipmap.basketball, "治愈")));
        list.add(new MySection(new Video(R.mipmap.biker, "悬疑")));
        list.add(new MySection(true, "动画", false));
        list.add(new MySection(new Video(R.mipmap.canoe, "同人")));
        list.add(new MySection(new Video(R.mipmap.football, "剧情")));
        list.add(new MySection(new Video(R.mipmap.cricket, "四格")));
        list.add(new MySection(new Video(R.mipmap.gymnast, "搞笑")));
        list.add(new MySection(true, "周边", false));
        list.add(new MySection(new Video(R.mipmap.snowboard, "画册")));
        list.add(new MySection(new Video(R.mipmap.sprinter, "手办")));
        list.add(new MySection(new Video(R.mipmap.standup_paddleboardi, "抱枕")));
        list.add(new MySection(new Video(R.mipmap.windsurf, "其他")));

        return list;
    }

    public static List<String> getStrData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String str = HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK;
            if (i % 2 == 0) {
                str = CYM_CHAD;
            }
            list.add(str);
        }
        return list;
    }

    public static List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE));
            list.add(new MultipleItem(MultipleItem.TEXT, MultipleItem.TEXT_SPAN_SIZE, CYM_CHAD));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN));
        }

        return list;
    }

}
