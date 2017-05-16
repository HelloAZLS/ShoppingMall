package ysg.gdcp.cn.shoppingmall.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.entity.HomeGoodsInfo;

/**
 * Created by Administrator on 2017/5/16 16:16.
 *
 * @author ysg
 */

public class MainFragmentGridViewAdapter extends BaseAdapter {

    private List<HomeGoodsInfo> mGoodsInfoList;
    private Context mContext;

    public MainFragmentGridViewAdapter(List<HomeGoodsInfo> goodsInfoList, Context context) {
        mGoodsInfoList = goodsInfoList;
        mContext = context;
    }

    @Override
    public int getCount() {

        return mGoodsInfoList != null ? mGoodsInfoList.size() : 0;
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

        View view = View.inflate(mContext, R.layout.grid_item, null);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv);
        if (mGoodsInfoList !=null){
            ivIcon.setImageResource(mGoodsInfoList.get(position).getIconId());
            tvTitle.setText(mGoodsInfoList.get(position).getIconName());
        }
        return view;


    }
}
