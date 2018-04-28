package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.operations.winsky.pulltorecyclerview.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name: LoadMoreListadapter
 * Author: xieganag
 * Email:
 * Comment: //TODO
 * Date: 2018-04-27 17:24
 */

public class LoadMoreListadapter extends BaseAdapter {
    private List<String> stringList;
    private Context context;

    public LoadMoreListadapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }
    public void inidata(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if ((convertView == null)) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(stringList.get(i));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
