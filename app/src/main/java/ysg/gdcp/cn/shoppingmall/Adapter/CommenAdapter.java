package ysg.gdcp.cn.shoppingmall.Adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 07:32.
 *
 * @author ysg
 */

public abstract class CommenAdapter<T> extends BaseAdapter {

    List<T> mList = null;

    public CommenAdapter(List<T> list) {
        mList = list;
    }


    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
