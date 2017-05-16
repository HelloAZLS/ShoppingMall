package ysg.gdcp.cn.shoppingmall.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/5/15 21:10.
 *
 * @author ysg
 */

public class MyMainFragmentAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImaRes;

    public MyMainFragmentAdapter(Context context, int[] imaRes) {
        mContext = context;
        mImaRes = imaRes;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %=mImaRes.length;
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(mImaRes[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
