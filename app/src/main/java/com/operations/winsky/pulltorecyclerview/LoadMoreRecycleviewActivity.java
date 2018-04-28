package com.operations.winsky.pulltorecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import adapter.LoadMoreRecycledapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import loadmoreview.LoadMoreRecyclerView;
import pulltorefush.WrapContentLinearLayoutManager;

/**
 * 简单的自定义Recycleview的 下拉刷新 上拉加载
 */
public class LoadMoreRecycleviewActivity extends AppCompatActivity {

    @Bind(R.id.rv_injury_list)
    LoadMoreRecyclerView recyclerView;
    @Bind(R.id.srl_injury)
    SwipeRefreshLayout swipeRefreshLayout;
    //当前已经加载完成的页码，第一页为1
    private LoadMoreRecycledapter mAdapter;
    private List<String> channelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more_recycleview);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new LoadMoreRecycledapter(LoadMoreRecycleviewActivity.this, channelList);
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setColorSchemeColors(this.getResources().getColor(R.color.main_red1));
        swipeRefreshLayout.setRefreshing(true);
        recyclerView.setLoadMoreEnable(true);
        for (int i = 0; i < 5; i++) {
            channelList.add(i + "22");
        }
        mAdapter.inidata(LoadMoreRecycleviewActivity.this, channelList);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 550);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                for (int i = 0; i < 10; i++) {
                    channelList.add(i + "22");
                }
                mAdapter.inidata(LoadMoreRecycleviewActivity.this, channelList);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 550);
            }
        });
        recyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                for (int i = 0; i < 10; i++) {
                    channelList.add(i + "22");
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (channelList.size() > 30) {
                            recyclerView.setLoadMoreError();//加载失败 点击之后相当于又下拉刷新了一遍
                            return;
                        }
                        if (channelList.size() > 50) {
                            recyclerView.setNoData(true);//设置没有更多数据了
                        } else {
                            try {
                                recyclerView.setLoadMoreFinished(10);//此次加载更多完成后调用，不需调用notify方法
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        ;
                    }
                }, 550);
                mAdapter.inidata(LoadMoreRecycleviewActivity.this, channelList);
            }
        });

    }
}
