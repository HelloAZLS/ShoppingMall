package ysg.gdcp.cn.toolbar.Utils;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ysg.gdcp.cn.toolbar.R;

/**
 * Created by Administrator on 2017/5/16 08:24.
 *
 * @author ysg
 */

public class ToolbarHelper {
    private Context mContext;
    private FrameLayout mcontentView;
    private Toolbar mToolbar;

    public ToolbarHelper(Context context, int layoutid) {
        mContext = context;
        //初始化整个内容
        initContentView();
        //初始化用户布局
        initUserLayout(layoutid);
        //初始化toolbar
        initToolbar();

    }

    private void initUserLayout(int layoutid) {
        View view = View.inflate(mContext, layoutid, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
    }


    private void initToolbar() {
        View view = View.inflate(mContext, R.layout.activity_toolbar, mcontentView);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

    }

    //初始化布局
    private void initContentView() {
        mcontentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        mcontentView.setLayoutParams(params);

    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public  View getContentView(){
        return  mcontentView;
    }

}
