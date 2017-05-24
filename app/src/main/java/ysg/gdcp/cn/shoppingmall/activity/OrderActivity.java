package ysg.gdcp.cn.shoppingmall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ysg.gdcp.cn.shoppingmall.R;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toast.makeText(this, "支付订单查询", Toast.LENGTH_SHORT).show();
        finish();
    }
}
