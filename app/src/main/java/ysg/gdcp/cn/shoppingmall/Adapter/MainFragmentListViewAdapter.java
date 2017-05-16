package ysg.gdcp.cn.shoppingmall.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.entity.GoodsInfo;

/**
 * Created by Administrator on 2017/5/16 19:44.
 *
 * @author ysg
 */

public class MainFragmentListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodsInfo.ResultBean.GoodlistBean> mDataList;

    public MainFragmentListViewAdapter(Context context, List<GoodsInfo.ResultBean.GoodlistBean> dataList) {
        mContext = context;

        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.goods_list_item, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle = (TextView) convertView.findViewById(R.id.title);
        holder.tvTitle.setText(mDataList.get(position).getTitle());

        return convertView;
    }

    static class ViewHolder {
        TextView tvTitle;
    }
}
