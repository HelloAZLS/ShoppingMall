package ysg.gdcp.cn.shoppingmall.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.entity.GoodsInfo;

/**
 * Created by Administrator on 2017/5/16 19:44.
 *
 * @author ysg
 */

public class MainFragmentListViewAdapter extends CommenAdapter {
    private Context mContext;
    private List<GoodsInfo.ResultBean.GoodlistBean> mDataList;

    public MainFragmentListViewAdapter( Context context, List<GoodsInfo.ResultBean.GoodlistBean> dataList) {
        super(dataList);
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = new ViewHolder();
//        if (convertView == null) {
////            convertView = View.inflate(mContext, R.layout.goods_list_item, null);
////            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
////        }
//        holder.tvTitle = (TextView) convertView.findViewById(R.id.title);
//        holder.tvTitle.setText(mDataList.get(position).getTitle());

        ViewHolder holder = ViewHolder.getHolder(mContext, R.layout.goods_list_item,convertView, parent);
        //标题
        TextView tvtitle  = holder.getView(R.id.title);
        tvtitle.setText(mDataList.get(position).getProduct());
        //描述
        TextView tvContent = holder.getView(R.id.tv_content);
        tvContent.setText(mDataList.get(position).getShort_title());
        //价格
        TextView tvPrice =holder.getView(R.id.price);
        tvPrice.setText(mDataList.get(position).getPrice());
        //数量
        TextView tvCount = holder.getView(R.id.count);
        tvCount.setText(mDataList.get(position).getBought()+"");
        //图片
        Uri uri =Uri.parse(mDataList.get(position).getImages().get(0).getImage());
        SimpleDraweeView sdv =holder.getView(R.id.iv_icon2);
        sdv.setImageURI(uri);
        return holder.getConverView();
    }


}
