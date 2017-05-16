package ysg.gdcp.cn.shoppingmall.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 15:41.
 *
 * @author ysg
 */

public class MainFragmentGoodsKindsAdapter extends PagerAdapter {


    private List<View> mGoodsList;

    public MainFragmentGoodsKindsAdapter(List<View> goodsList) {


        mGoodsList = goodsList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mGoodsList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mGoodsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
