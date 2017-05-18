package ysg.gdcp.cn.shoppingmall.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import ysg.gdcp.cn.shoppingmall.Adapter.MainFragmentGoodsKindsAdapter;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.entity.GoodsDetailInfo;

public class ImageActivity extends AppCompatActivity {

    private ViewPager mVpImage;
    private List<View> mViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mVpImage = (ViewPager) findViewById(R.id.vp_image);
        initData();

    }

    private void initData() {
        GoodsDetailInfo detailInfo = (GoodsDetailInfo) getIntent().getSerializableExtra("detailInfo");
        List<String> detail_imags = detailInfo.getResult().getDetail_imags();

        for (int i = 0; i < detail_imags.size(); i++) {
            View imagVIew = View.inflate(this, R.layout.page_image_item, null);
            SimpleDraweeView ivImage = (SimpleDraweeView) imagVIew.findViewById(R.id.iv_image);

            Uri uri = Uri.parse(detail_imags.get(i));
            ivImage.setImageURI(uri);
            mViewList.add(imagVIew);

        }
        mVpImage.setAdapter(new MainFragmentGoodsKindsAdapter(mViewList));
    }
}
