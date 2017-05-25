package ysg.gdcp.cn.shoppingmall.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.Utils.BmobManager;
import ysg.gdcp.cn.shoppingmall.Utils.Config;
import ysg.gdcp.cn.shoppingmall.Utils.QueueUtils;
import ysg.gdcp.cn.shoppingmall.entity.FavorInfo;
import ysg.gdcp.cn.shoppingmall.entity.GoodsDetailInfo;
import ysg.gdcp.cn.shoppingmall.listener.MyBmobQueryAllListener;
import ysg.gdcp.cn.shoppingmall.listener.MyBmobQueryCallBack;
import ysg.gdcp.cn.shoppingmall.listener.MyBmobQueryGIdListener;
import ysg.gdcp.cn.shoppingmall.nohttp.MyHttpListener;
import ysg.gdcp.cn.shoppingmall.view.MyScrollView;

/**
 * Created by Administrator on 2017/5/18 09:58.
 *
 * @author ysg
 */
public class DetailActivity extends AppCompatActivity implements MyHttpListener, MyScrollView.ScrollViwListener {


    @Bind(R.id.iv_detail)
    SimpleDraweeView mIvDetail;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_decs)
    TextView mTvDecs;
    @Bind(R.id.textView)
    TextView mTextView;
    @Bind(R.id.tv_bought)
    TextView mTvBought;
    @Bind(R.id.tv_title2)
    TextView mTvTitle2;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.web_detail)
    WebView mWebDetail;
    @Bind(R.id.web_notice)
    WebView mWebNotice;
    @Bind(R.id.list_recommend)
    ListView mListRecommend;
    @Bind(R.id.scrollView)
    MyScrollView mScrollView;
    @Bind(R.id.tv_titlebar)
    TextView mTvTitlebar;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.iv_favor)
    ImageView mIvFavor;
    @Bind(R.id.iv_share)
    ImageView mIvShare;
    @Bind(R.id.layout_title)
    RelativeLayout mLayoutTitle;
    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_value)
    TextView mTvValue;
    @Bind(R.id.btn_buy)
    Button mBtnBuy;
    @Bind(R.id.layout_buy)
    RelativeLayout mLayoutBuy;
    private GoodsDetailInfo mDetailInfo;
    private int mHeight;

    private boolean isFavor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        String goods_id = getIntent().getStringExtra("goods_id");
        //商品详情页面数据解析
        Request<String> request = NoHttp.createStringRequest(Config.baseUrl + goods_id + ".txt", RequestMethod.GET);
        QueueUtils.getInstance().add(this, 0, request, this, true, true);
        //初始化favor
        initFavor();
        //设置ScrollView的监听
        initListener();
    }


    private void initFavor() {
        BmobManager.getInstance().queryAllData(new FavorInfo(), false, "isFavor", true);
        BmobManager.getInstance().setListener(new MyBmobQueryAllListener() {
            @Override
            public void queryAllFalure(BmobException e) {
                mIvFavor.setImageResource(R.drawable.icon_uncollected);
            }

            @Override
            public void queryAllSucess(List<FavorInfo> list) {
                for (int i = 0; i < list.size(); i++) {
                    FavorInfo favorInfo = list.get(i);
                    if (favorInfo.getGoodsId().equals(mDetailInfo.getResult().getGoods_id())) {
                        BmobManager.getInstance().queryData(new FavorInfo(), favorInfo.getObjectId());
                        BmobManager.getInstance().setListener(new MyBmobQueryCallBack() {
                            @Override
                            public void querySucess(FavorInfo favorInfo) {
                                boolean favor = favorInfo.isFavor();
                                Log.i("initData", " " + favor);
                                mIvFavor.setImageResource(favor ? R.drawable.icon_collected_black : R.drawable.icon_uncollected);
                                isFavor = favor ? true : false;
                            }
                            @Override
                            public void queryFalure(BmobException e) {

                            }
                        });
                    } else {
                        mIvFavor.setImageResource(R.drawable.icon_uncollected);
                    }
                }
            }
        });
    }

    //设置ScrollView的监听
    private void initListener() {
        ViewTreeObserver viewTreeObserver = mIvDetail.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mIvDetail.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mHeight = mIvDetail.getHeight();
                mScrollView.setScrollViwListener(DetailActivity.this);
            }
        });
    }


    @Override
    public void onSucceed(int what, Response response) {
        Gson gson = new Gson();
        switch (what) {
            case 0:
                mDetailInfo = gson.fromJson((String) response.get(), GoodsDetailInfo.class);
                //详情页面信息
                String details = mDetailInfo.getResult().getDetails();
                //温馨提示信息
                String notice = mDetailInfo.getResult().getNotice();

                if (mDetailInfo != null) {
                    mWebDetail.loadDataWithBaseURL("about:blank", details, "text/html", "UTF-8", "");
                    mWebNotice.loadDataWithBaseURL("about:blank", notice, "text/html", "UTF-8", "");
                    mTvTitle.setText(mDetailInfo.getResult().getTitle());
                    mTvDecs.setText(mDetailInfo.getResult().getTitle());
                    mTvBought.setText(mDetailInfo.getResult().getValue());
                    mTvAddress.setText(mDetailInfo.getResult().getAddress_id() + "");
                    mTvPrice.setText(mDetailInfo.getResult().getPrice() + "");
                    Uri uri = Uri.parse(mDetailInfo.getResult().getImages().get(0).getImage());
                    mIvDetail.setImageURI(uri);

                }
                break;
        }

    }

    @Override
    public void onFailed(int what, Response response) {

    }

    @OnClick({R.id.iv_detail, R.id.iv_back, R.id.iv_favor, R.id.iv_share, R.id.btn_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_detail:

                Intent intent = new Intent(DetailActivity.this, ImageActivity.class);
                intent.putExtra("detailInfo", mDetailInfo);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_favor:
                //收藏功能实现
                FavorInfo favorInfo = new FavorInfo();
                GoodsDetailInfo.ResultBean bean = mDetailInfo.getResult();
                favorInfo.setGoodsId(bean.getGoods_id());
                favorInfo.setPropduct(bean.getProduct());
                favorInfo.setPrice(bean.getPrice());
                favorInfo.setValue(bean.getValue());
                favorInfo.setImageUrl(bean.getImages().get(0).getImage());
                if (!isFavor) {
                    //收藏
                    mIvFavor.setImageResource(R.drawable.icon_collected_black);
                    favorInfo.setFavor(true);
                    isFavor =true;
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                    BmobManager.getInstance().insertData(favorInfo);
                } else {
                    //取消收藏
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    mIvFavor.setImageResource(R.drawable.icon_uncollected);
                    //取消收藏
                    deteleteData();
                    isFavor =false;
//                    BmobManager.getInstance().deteleData(favorInfo);

                }

                break;
            case R.id.iv_share:
                break;
            case R.id.btn_buy:
                break;
        }
    }

    //取消收藏方法
    private void deteleteData() {
        BmobManager.getInstance().queryGoodsId(new FavorInfo(),mDetailInfo.getResult().getGoods_id());
        BmobManager.getInstance().setListener(new MyBmobQueryGIdListener() {
            @Override
            public void queryGIdSUcess(FavorInfo favorInfo) {
                //删除收藏的数据
                BmobManager.getInstance().deteleData(new FavorInfo(),favorInfo.getObjectId());
            }
        });

    }


    @Override
    public void onScrollChange(MyScrollView scrollView, int x, int y, int oldX, int oldY) {
        if (y <= 0) {
            mTvTitlebar.setVisibility(View.GONE);
            mLayoutTitle.setBackgroundColor(Color.argb(0, 0, 0, 0));
        } else if (y > 0 && y < mHeight) {
            float scal = (float) y / mHeight;
            float alph = 255 * scal;
            mTvTitlebar.setVisibility(View.VISIBLE);
            mTvTitlebar.setText(mDetailInfo.getResult().getProduct());
            mTvTitlebar.setTextColor(Color.argb((int) alph, 0, 0, 0));
            mLayoutTitle.setBackgroundColor(Color.argb((int) alph, 255, 255, 255));
        } else {
            mTvTitlebar.setVisibility(View.VISIBLE);
            mTvTitlebar.setText(mDetailInfo.getResult().getProduct());
            mTvTitlebar.setTextColor(Color.argb(0, 0, 0, 0));
            mLayoutTitle.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }
    }
}
