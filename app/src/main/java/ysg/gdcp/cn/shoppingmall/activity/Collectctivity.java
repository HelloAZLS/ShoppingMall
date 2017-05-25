package ysg.gdcp.cn.shoppingmall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import ysg.gdcp.cn.shoppingmall.Adapter.CommenAdapter;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.Utils.BmobManager;
import ysg.gdcp.cn.shoppingmall.entity.FavorInfo;
import ysg.gdcp.cn.shoppingmall.listener.MyBmobQueryAllListener;

public class Collectctivity extends AppCompatActivity {

    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.lv_collect)
    ListView mLvCollect;
    @Bind(R.id.back_photo_img)
    ImageView mBackPhotoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectctivity);
        ButterKnife.bind(this);
        //初始化titlebar
        initTitlebar();
        //初始化listVIew适配器
        initLvAdapter();
    }

    private void initLvAdapter() {
        BmobManager.getInstance().queryAllData(new FavorInfo(),true,"isFavor",true);
        BmobManager.getInstance().setListener(new MyBmobQueryAllListener() {
            @Override
            public void queryAllFalure(BmobException e) {

            }

            @Override
            public void queryAllSucess(final List<FavorInfo> list) {
                mLvCollect.setAdapter(new CommenAdapter<FavorInfo>(list) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = View.inflate(Collectctivity.this, R.layout.goods_list_item, null);
                        TextView tvTitle = (TextView) view.findViewById(R.id.title);
                        tvTitle.setText(list.get(position).getPropduct()+"");
                        return view;
                    }
                });
            }
        });
    }

    private void initTitlebar() {
        mTitleTv.setVisibility(View.VISIBLE);
        mTitleTv.setText("收藏");
        mBackPhotoImg.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.back_photo_img)
    public void onClick() {
        finish();
    }
}
