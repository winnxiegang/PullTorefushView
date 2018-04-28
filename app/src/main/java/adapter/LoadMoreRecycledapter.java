package adapter; /**
 * Name: adapter.LoadMoreRecycledapter
 * Author: xieganag
 * Email:
 * Comment: //TODO
 * Date: 2018-04-24 17:05
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.operations.winsky.pulltorecyclerview.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lingjiu on 2017/3/30.
 */

public class LoadMoreRecycledapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;

    public LoadMoreRecycledapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void inidata(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
        //以下使用会出现Android RecyclerView 中的 item 不居中
//        View view = View.inflate(context, R.layout.layout_item, null);
//
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}

