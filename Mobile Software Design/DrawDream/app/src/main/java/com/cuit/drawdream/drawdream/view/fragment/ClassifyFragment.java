package com.cuit.drawdream.drawdream.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.view.adapter.PullToRefreshAdapter;
import com.cuit.drawdream.drawdream.model.service.DataServer;


public class ClassifyFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;
    private PullToRefreshAdapter pullToRefreshAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private static final int PAGE_SIZE = 6;
    private boolean isErr;

    public ClassifyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initAdapter();
        return view;
    }

    private void initAdapter() {
        pullToRefreshAdapter = new PullToRefreshAdapter();
        pullToRefreshAdapter.setOnLoadMoreListener(this, mRecyclerView);
        pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //设置adapter
        mRecyclerView.setAdapter(pullToRefreshAdapter);
        mCurrentCounter = pullToRefreshAdapter.getData().size();

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onRefresh() {
        pullToRefreshAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(() -> {
                pullToRefreshAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
                pullToRefreshAdapter.setEnableLoadMore(true);

        }, delayMillis);
    }
}
