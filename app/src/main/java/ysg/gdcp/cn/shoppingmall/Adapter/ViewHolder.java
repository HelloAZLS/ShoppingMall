package ysg.gdcp.cn.shoppingmall.Adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/5/18 07:32.
 *
 * @author ysg
 */

public class ViewHolder {

    private View mConverView;

    SparseArray<View> mViews = null;

    public ViewHolder(Context context, int layoutId, ViewGroup viewGroup) {
            mViews = new SparseArray<>();
            mConverView = View.inflate(context, layoutId, null);
            mConverView.setTag(this);
    }

    public static ViewHolder getHolder(Context context, int layoutId, View converView, ViewGroup viewGroup) {
        if (converView == null) {
            return new ViewHolder(context, layoutId, viewGroup);
        }
        return (ViewHolder) converView.getTag();
    }

    public <T extends View> T getView(int viewID) {
        View view = mViews.get(viewID);
        if (view == null) {
            view = mConverView.findViewById(viewID);
            mViews.put(viewID, view);
        }
        return (T) view;
    }

    public View getConverView() {
        return mConverView;
    }

}
