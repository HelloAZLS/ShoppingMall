package ysg.gdcp.cn.shoppingmall.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.fragment.AroundFragment;
import ysg.gdcp.cn.shoppingmall.fragment.MainFragment;
import ysg.gdcp.cn.shoppingmall.fragment.MoreFragment;
import ysg.gdcp.cn.shoppingmall.fragment.MyFragment;

public class MainActivity extends FragmentActivity {
    private Class[] fragents = {MainFragment.class, AroundFragment.class, MyFragment.class, MoreFragment.class};
    private FragmentTabHost mFgTh;
    private int[] tabSelector = {R.drawable.ic_tab_artists_selector, R.drawable.ic_tab_albums_selector,
            R.drawable.ic_tab_songs_selector, R.drawable.ic_tab_playlists_selector};
    private String[] content = {"首页", "周边", "我的", "更多"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFgTh = (FragmentTabHost) findViewById(R.id.fg_th);
        mFgTh.setup(MainActivity.this, getSupportFragmentManager(), android.R.id.tabcontent);
        //初始化用户登录
        initUserTime();
        for (int i = 0; i < fragents.length; i++) {
            View tabView = View.inflate(MainActivity.this, R.layout.tab_item, null);
            TextView tvContent = (TextView) tabView.findViewById(R.id.tv_tab);
            ImageView ivIcon = (ImageView) tabView.findViewById(R.id.iv_tab);
            tvContent.setText(content[i]);
            ivIcon.setImageResource(tabSelector[i]);
            mFgTh.addTab(mFgTh.newTabSpec("" + i).setIndicator(tabView), fragents[i], null);
        }


    }

    private void initUserTime() {
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        long time = sp.getLong("time", 0);
        if (time > 0) {
            //上次用户登录时间，与当前洗头时间相差30天，就要重新登录(2592000000)
            long preTime  = System.currentTimeMillis() - time;
            if(preTime>(30*24*60*60*1000)){
                sp.edit().putBoolean("isLogin", false).commit();
            }
        }
    }
}
