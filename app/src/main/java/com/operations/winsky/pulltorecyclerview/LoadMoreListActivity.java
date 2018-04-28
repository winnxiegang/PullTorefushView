package com.operations.winsky.pulltorecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.LoadMoreListadapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import loadmore.OnLoadMoreListener;
import loadmoreview.LoadMoreListView;

public class LoadMoreListActivity extends AppCompatActivity {
    LoadMoreListadapter loadMoreListadapter;
    @Bind(R.id.listmore_item)
    LoadMoreListView listmoreItem;
    private List<String> stringList = new ArrayList<>();
    boolean aBooleanEnd=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more_list);
        ButterKnife.bind(this);
        for (int i = 0; i < 20; i++) {
            stringList.add(i + "22");
        }
        loadMoreListadapter = new LoadMoreListadapter(stringList, this);
        listmoreItem.initBottomView(true);
        listmoreItem.setAdapter(loadMoreListadapter);
        listmoreItem.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (aBooleanEnd) return;//加载完毕后再下拉 就不需要请求了。
                for (int i = 0; i < 10; i++) {
                    stringList.add(i + "22");
                }
                if (stringList.size() >=30) {
                    listmoreItem.endData(true);//设置没有更多了 加载完毕。
                    aBooleanEnd=true;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (aBooleanEnd) return;
                        loadMoreListadapter.inidata(LoadMoreListActivity.this, stringList);
                    }
                }, 1050);
            }
        });
    }
}
