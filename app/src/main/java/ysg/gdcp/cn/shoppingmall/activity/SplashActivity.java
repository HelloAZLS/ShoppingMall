package ysg.gdcp.cn.shoppingmall.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import ysg.gdcp.cn.shoppingmall.MainActivity;
import ysg.gdcp.cn.shoppingmall.R;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                boolean isFirst = sp.getBoolean("isFirst", true);
                Intent view = new Intent();
                if (isFirst) {
                    view.setClass(SplashActivity.this, GuideActivity.class);
                } else {
                    view.setClass(SplashActivity.this, MainActivity.class);
                }
                startActivity(view);
                overridePendingTransition(R.anim.push_botton_in,0);
                finish();


            }
        }, 2000);
    }
}