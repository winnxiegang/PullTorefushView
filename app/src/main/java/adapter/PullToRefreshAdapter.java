package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.operations.winsky.pulltorecyclerview.R;
import com.operations.winsky.pulltorecyclerview.RecyclerBaseAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Name: PullToRefreshAdapter
 * Author: xieganag
 * Email:
 * Date: 2018-04-28 09:35
 */

public class PullToRefreshAdapter extends RecyclerBaseAdapter<String> {
    @Bind(R.id.tv)
    TextView tv;

    public PullToRefreshAdapter(Context context, List<String> list, View header) {
        super(context, list, header);
    }

    /**
     * 如果parent传进去为null，生成的View的LayoutParams为null，在RecyclerView.addView时，发现LayoutParams为null，则生成默认的LayoutParams,
     * [java] view plain copy
     * // android.view.ViewGroup
     * protected LayoutParams generateDefaultLayoutParams() {
     * return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
     * }
     * <p>
     * 所以无论无论你怎么写，最外层的LinearLayout宽度为WRAP_CONTENT，如果那三个点的宽度为6dp，那么整个View的宽度也为6dp，所以无法居中。
     * <p>
     * 所以要解决照这个问题需要在inflate的时候将parent传进去，如：
     * [java] view plain copy
     * result = new DividerHolder(mInflater.inflate(R.layout.divider, parent, false));
     *
     * @param context
     * @param parent
     * @return
     */
    @Override
    protected BaseViewHolder getViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void setData(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.titleTv.setText(list.get(position));
    }

    public class ViewHolder extends BaseViewHolder {


        @Bind(R.id.tv)
        TextView titleTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
