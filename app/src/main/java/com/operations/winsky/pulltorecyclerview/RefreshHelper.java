package com.operations.winsky.pulltorecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import loadmore.OnLoadMoreListener;
import pulltorefush.PullToRefreshView;

import static com.operations.winsky.pulltorecyclerview.RefreshLayoutDirection.*;

/**
 * Name: com.operations.winsky.pulltorecyclerview.RefreshHelper
 * Author: xieganag
 * Email:
 * Comment: //TODO
 * Date: 2018-04-24 17:32
 */

public class RefreshHelper implements OnLoadMoreListener, PullToRefreshView.OnRefreshListener {
    private RecyclerBaseAdapter baseRecyclerAdapter;

    private RecyclerView recyclerView;

    private PullToRefreshView refreshLayout;

    private RefreshHelper.OnRefreshListener onRefreshListener;

    private RefreshLayoutDirection direction;

    public RefreshHelper(View view, RefreshLayoutDirection direction, final RefreshHelper.OnRefreshListener listener) {

        if (view.findViewById(R.id.refreshLayout) == null) {

            throw new IllegalArgumentException("activity没有包含refreshLayout的ID");
        }

        refreshLayout = ((PullToRefreshView) view.findViewById(R.id.refreshLayout));

        recyclerView = ((RecyclerView) view.findViewById(R.id.recyclerView));

        baseRecyclerAdapter = ((RecyclerBaseAdapter) recyclerView.getAdapter());

        if (baseRecyclerAdapter == null) {

            throw new IllegalStateException("必须先设置adapter");
        }

        if (direction != null) {
            this.direction = direction;
        } else {
            this.direction = BOTH;
        }

        if (listener != null) {
            onRefreshListener = listener;
        }

        baseRecyclerAdapter.setOnLoadMoreListener(this);

        refreshLayout.setOnRefreshListener(this);

        setRefresh();
    }

    private void setRefresh() {
        switch (direction) {
            case BOTH:
                refreshLayout.setEnabled(true);

                baseRecyclerAdapter.setLoadMoreEnable(true);
                break;
            case BOTTOM:
                refreshLayout.setEnabled(false);

                baseRecyclerAdapter.setLoadMoreEnable(true);
                break;
            case TOP:
                refreshLayout.setEnabled(true);

                baseRecyclerAdapter.setLoadMoreEnable(false);
                break;

            case NONE:
                break;
        }

    }

    public void closeRefresh(String requestType) {
        if (requestType == AppParam.REFRESH_DATA) {

            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMore() {
        baseRecyclerAdapter.showLoadMoreLoading();
        if (onRefreshListener != null) {

            onRefreshListener.onLoadMore();
        }
    }


    @Override
    public void onRefresh() {
        if (onRefreshListener != null) {

            onRefreshListener.onRefresh();
        }
    }


    public static interface OnRefreshListener {

        public void onRefresh();

        public void onLoadMore();
    }
}
