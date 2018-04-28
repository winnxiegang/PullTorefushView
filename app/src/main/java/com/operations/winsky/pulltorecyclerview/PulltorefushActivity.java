package com.operations.winsky.pulltorecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.PullToRefreshAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import pulltorefush.PullToRefreshView;

public class PulltorefushActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    PullToRefreshView refreshLayout;
    private PullToRefreshAdapter mAdapter;
    private List<String> channelList = new ArrayList<>();
    private RefreshHelper refreshHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefush);
        ButterKnife.bind(this);
        //添加头部
        View headview = LayoutInflater.from(this).inflate(R.layout.layout_item, null, false);
        mAdapter = new PullToRefreshAdapter(this, channelList, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        refreshHelper = new RefreshHelper(refreshLayout, RefreshLayoutDirection.BOTH, new RefreshHelper.OnRefreshListener() {
            @Override
            public void onRefresh() {
                channelList.clear();
                for (int i = 0; i < 10; i++) {
                    channelList.add(i + "A");
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshHelper.closeRefresh(AppParam.REFRESH_DATA);
                    }
                }, 550);
            }

            @Override
            public void onLoadMore() {
                mAdapter.showLoadMoreLoading();
//                if (channelList.size() > 40) {
//                   // mAdapter.showLoadMoreRetry();//加载失败 从新重试问题
//                    mAdapter.showLoadMoreEnd();//没有更多了
//                    return;
//                }
                for (int i = 0; i < 10; i++) {
                    channelList.add(i + "B");
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                }, 550);
            }
        });

        refreshHelper.onRefresh();
        refreshHelper.onLoadMore();
    }
}