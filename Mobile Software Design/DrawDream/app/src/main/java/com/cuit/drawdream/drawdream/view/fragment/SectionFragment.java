package com.cuit.drawdream.drawdream.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.MySection;
import com.cuit.drawdream.drawdream.model.service.DataServer;
import com.cuit.drawdream.drawdream.view.adapter.SectionAdapter;

import java.util.List;

/**
 * Created by sunnylu on 2017/5/26.
 */

public class SectionFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<MySection> mData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.section_list);
        //两列的GridLayout
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        mData = DataServer.getSampleData();
        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,R.layout.item_section_head,mData);
        mRecyclerView.setAdapter(sectionAdapter);
        return view;
    }
}
