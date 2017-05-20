package ysg.gdcp.cn.shoppingmall.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/5/18 21:50.
 *
 * @author ysg
 */

public class MyScrollView extends ScrollView {

    private ScrollViwListener mScrollViwListener =null;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public  interface  ScrollViwListener{
        void onScrollChange(MyScrollView scrollView,int x,int y,int oldX,int oldY);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViwListener!=null){
            mScrollViwListener.onScrollChange(this, l, t, oldl, oldt);
        }
    }

    public void setScrollViwListener(ScrollViwListener scrollViwListener){
        mScrollViwListener =scrollViwListener;
    }
}
