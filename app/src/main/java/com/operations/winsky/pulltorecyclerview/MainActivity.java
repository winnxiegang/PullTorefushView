package com.operations.winsky.pulltorecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.to_listview)
    Button toListview;
    @Bind(R.id.to_recycleview)
    Button toRecycleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.to_listview, R.id.to_recycleview, R.id.to_PulltorefushActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_listview:
                startActivity(new Intent(this, LoadMoreListActivity.class));
                break;
            case R.id.to_recycleview:
                startActivity(new Intent(this, LoadMoreRecycleviewActivity.class));
                break;
            case R.id.to_PulltorefushActivity:
                startActivity(new Intent(this, PulltorefushActivity.class));
                break;
        }
    }
}
