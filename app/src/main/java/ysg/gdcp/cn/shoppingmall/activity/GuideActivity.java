package ysg.gdcp.cn.shoppingmall.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import ysg.gdcp.cn.shoppingmall.R;

public class GuideActivity extends AppCompatActivity {

    private ViewPager mVpGuide;
    private ArrayList<View> mImageList;
    private int mImages[] = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3, R.mipmap.guide_4};
    private Button mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
        initDatas();
        mVpGuide.setAdapter(new MyPagerAdapter());

    }

    private void initDatas() {
        mImageList = new ArrayList<>();
        for (int image : mImages) {
            ImageView view = new ImageView(GuideActivity.this);
            view.setBackgroundResource(image);
            mImageList.add(view);
        }
    }

    private void initViews() {
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                sp.edit().putBoolean("isFirst",false).commit();
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        mVpGuide = (ViewPager) findViewById(R.id.vp_guide);
        mVpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageList.size() - 1) {
                    mBtnStart.setVisibility(View.VISIBLE);
                    //加载显示动画
                    Animation anima = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.anim_gudie_start_btn);
                    mBtnStart.startAnimation(anima);
                } else {
                    mBtnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mImageList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
