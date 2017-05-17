package com.cuit.drawdream.drawdream.view.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cuit.drawdream.drawdream.R;


/**
 * ClassName : PreviousView
 * Created by yangq
 * On 17-3-16.
 * Desc :公用的返回布局
 */

public class PreviousView extends RelativeLayout {

    private TextView tvTitlePv;
    private RelativeLayout btnBackPV;

    public PreviousView(Context context) {
        super(context);
        initUI(context);
    }

    public PreviousView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public PreviousView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    private void initUI(Context context){
        LayoutInflater.from(context).inflate(R.layout.previous_view,this);
        tvTitlePv = (TextView)findViewById(R.id.tv_title_pv);
        btnBackPV = (RelativeLayout)findViewById(R.id.btn_back_pv);
        btnBackPV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回的页面都直接finish掉，然后返回主页
                ((Activity)getContext()).finish();
            }
        });
    }

    /**
     * 提供给外部设置标题
     * @param title
     */
    public void setTitle(String title){
        tvTitlePv.setText(title);
    }

}
