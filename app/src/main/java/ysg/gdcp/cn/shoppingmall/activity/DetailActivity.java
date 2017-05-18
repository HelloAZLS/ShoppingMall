package ysg.gdcp.cn.shoppingmall.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.Utils.Config;
import ysg.gdcp.cn.shoppingmall.Utils.QueueUtils;
import ysg.gdcp.cn.shoppingmall.entity.GoodsDetailInfo;
import ysg.gdcp.cn.shoppingmall.nohttp.MyHttpListener;

/**
 * Created by Administrator on 2017/5/18 09:58.
 *
 * @author ysg
 */
public class DetailActivity extends AppCompatActivity implements MyHttpListener {


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
    ScrollView mScrollView;
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

//    private WebView mWebDetail;
//    private WebView mWebNotice;
//    private TextView mTvTitle;
//    private TextView mTvDecs;
//    private TextView mTvBought;
//    private ImageView mIvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        String goods_id = getIntent().getStringExtra("goods_id");
        //商品详情页面数据解析
        Request<String> request = NoHttp.createStringRequest(Config.baseUrl + goods_id + ".txt", RequestMethod.GET);
        QueueUtils.getInstance().add(this, 0, request, this, true, true);
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
                    mTvAddress.setText(mDetailInfo.getResult().getAddress_id()+"");
                    mTvPrice.setText(mDetailInfo.getResult().getPrice()+"");
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

                Intent intent = new Intent(DetailActivity.this,ImageActivity.class);
                intent.putExtra("detailInfo",mDetailInfo);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_favor:
                break;
            case R.id.iv_share:
                break;
            case R.id.btn_buy:
                break;
        }
    }


}
