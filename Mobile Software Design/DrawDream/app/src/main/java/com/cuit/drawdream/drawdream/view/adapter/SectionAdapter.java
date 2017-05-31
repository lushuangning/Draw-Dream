package com.cuit.drawdream.drawdream.view.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cuit.drawdream.drawdream.bean.MySection;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.Video;

import java.util.List;

/**
 * Created by sunnylu on 2017/5/26.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<MySection,BaseViewHolder> {

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.section_header, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        Video video = (Video) item.t;
        helper.setImageResource(R.id.section_item_iv, video.getImg());
        helper.setText(R.id.section_item_tv, video.getName());
    }
}
