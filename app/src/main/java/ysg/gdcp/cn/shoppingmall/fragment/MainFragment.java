package ysg.gdcp.cn.shoppingmall.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import ysg.gdcp.cn.shoppingmall.Adapter.MainFragmentGoodsKindsAdapter;
import ysg.gdcp.cn.shoppingmall.Adapter.MainFragmentGridViewAdapter;
import ysg.gdcp.cn.shoppingmall.Adapter.MainFragmentListViewAdapter;
import ysg.gdcp.cn.shoppingmall.Adapter.MyMainFragmentAdapter;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.Utils.ContantsPool;
import ysg.gdcp.cn.shoppingmall.Utils.QueueUtils;
import ysg.gdcp.cn.shoppingmall.activity.DetailActivity;
import ysg.gdcp.cn.shoppingmall.entity.FilmInfo;
import ysg.gdcp.cn.shoppingmall.entity.GoodsInfo;
import ysg.gdcp.cn.shoppingmall.entity.HomeGoodsInfo;
import ysg.gdcp.cn.shoppingmall.nohttp.MyHttpListener;
import ysg.gdcp.cn.shoppingmall.view.Indicator;

import static ysg.gdcp.cn.shoppingmall.Utils.Config.filmUrl;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements ContantsPool, MyHttpListener, View.OnClickListener {

    private ViewPager mVpFrag; //新闻滚动ViewPager
    private int[] imgRes = {R.drawable.banner01, R.drawable.banner02, R.drawable.banner03};//新闻滚动ViewPager图片资源
    private Handler mHandler = new Handler();
    private Indicator mImdicator;//新闻滚动ViewPager指示器
    private ViewPager mVpGoodsKinds;//商品分类viewpager
    //商品分类viewpager的内部控件集合
    private List<View> mGoodsList = new ArrayList();
    //第一页商品信息数据
    private List<HomeGoodsInfo> mOneHomeGoodsInfos = new ArrayList<>();
    //第二页商品信息数据

    //存放商品信息容器
    private List<GoodsInfo.ResultBean.GoodlistBean> mDataList = new ArrayList<>();
    private List<HomeGoodsInfo> mTowHomeGoodsInfos = new ArrayList<>();
    private MainFragmentGoodsKindsAdapter mMainFragmentGoodsKindsAdapter;
    //电影列表标题
    private String[] mGoodsTitle;
    //PullToRefreshListView
    private PullToRefreshListView mPullToRefreshListView;
    //listView 头布局
    private View mHeadView;
    //电影列表
    private LinearLayout mLayoutFilm;
    //listView
    private ListView mLvMainFragment;
    private LinearLayout mLocationLIn;
    private TextView mCityName;
    private TextView mTitleTv;
    private LinearLayout mInputLine;
    private ImageView mScanImg;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mian, container, false);

        initTitleBar(view);
        //ListVIew
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_mainFragment);
        //ListView头布局
        mHeadView = View.inflate(getActivity(), R.layout.fragment_main_head, null);
        //将PullToRefreshListView 转成ListVIew才能添加头布局
        mLvMainFragment = mPullToRefreshListView.getRefreshableView();
        mLvMainFragment.addHeaderView(mHeadView);
        //电影列表布局
        mLayoutFilm = (LinearLayout) view.findViewById(R.id.layout_film);

        //新闻滚动ViewPager
        mVpFrag = (ViewPager) mHeadView.findViewById(R.id.vp_mainfrag);
        //新闻滚动ViewPager指示器
        mImdicator = (Indicator) mHeadView.findViewById(R.id.indicator);

        //商品分类viewpager
        mVpGoodsKinds = (ViewPager) mHeadView.findViewById(R.id.vp_goodskinds);


        //新闻滚动ViewPager适配器
        MyMainFragmentAdapter adapter = new MyMainFragmentAdapter(getActivity(), imgRes);


        //初始化数据
        initData();
        //初始化商品分类的GridView
        initGridView();

        mVpFrag.setAdapter(adapter);
        //VIewPager设置监听
        mVpFrag.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                position %= imgRes.length;
                mImdicator.setViewPro(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //新闻自动轮播方法
        autoScroll();
        //给listView设置适配器
        mLvMainFragment.setAdapter(new MainFragmentListViewAdapter(getActivity(), mDataList));
        //给listVIew设置点击事件
        mLvMainFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("goods_id", mDataList.get(position - 1).getGoods_id());
                startActivity(intent);
            }
        });
        //下拉刷新监听
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新操作
                new MyAsyns().execute();
            }
        });
        return view;

    }

    //初始化titlebar控件
    private void initTitleBar(View view) {
        //位置
        mLocationLIn = (LinearLayout) view.findViewById(R.id.location_lay);
        mInputLine = (LinearLayout) view.findViewById(R.id.inputLL);
        mInputLine.setVisibility(View.VISIBLE);
        mLocationLIn.setVisibility(View.VISIBLE);
        mScanImg = (ImageView) view.findViewById(R.id.scan_img);
        mScanImg.setVisibility(View.VISIBLE);
        mInputLine.setOnClickListener(this);
        mScanImg.setOnClickListener(this);
        mLocationLIn.setOnClickListener(this);
        mCityName = (TextView) view.findViewById(R.id.city_name);
//        标题
        mTitleTv = (TextView) view.findViewById(R.id.title_tv);
        mTitleTv.setText("首页");

    }

    private void initData() {
        //获取GridView的标题资源
        mGoodsTitle = getResources().getStringArray(R.array.goods_title);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.goods_icon);
        for (int i = 0; i < mGoodsTitle.length; i++) {
            if (i < 8) {
                mOneHomeGoodsInfos.add(new HomeGoodsInfo(mGoodsTitle[i], typedArray.getResourceId(i, 0)));
            } else {
                mTowHomeGoodsInfos.add(new HomeGoodsInfo(mGoodsTitle[i], typedArray.getResourceId(i, 0)));
            }
        }
        //请求猜你喜欢网络数据
        Request<String> recommendRequest = NoHttp.createStringRequest(spRecommendUrl, RequestMethod.GET);
        QueueUtils.getInstance().add(getActivity(), 0, recommendRequest, this, true, true);
        //请求电影列表网络数据
        Request<String> filmRequest = NoHttp.createStringRequest(filmUrl, RequestMethod.GET);
        QueueUtils.getInstance().add(getActivity(), 1, filmRequest, this, true, true);

    }

    private void initGridView() {
        View view = View.inflate(getActivity(), R.layout.mainfragment_goods_item, null);
        GridView gvMainFragmentGoods = (GridView) view.findViewById(R.id.gv_mainfragment_goods);
        gvMainFragmentGoods.setAdapter(new MainFragmentGridViewAdapter(mOneHomeGoodsInfos, getActivity()));
        //第二页
        View viewT = View.inflate(getActivity(), R.layout.mainfragment_goods_item, null);
        GridView gvMainFragmentGoodsT = (GridView) viewT.findViewById(R.id.gv_mainfragment_goods);
        gvMainFragmentGoodsT.setAdapter(new MainFragmentGridViewAdapter(mTowHomeGoodsInfos, getActivity()));

        mGoodsList.add(view);
        mGoodsList.add(viewT);

        //商品分类viewpager适配器
        mMainFragmentGoodsKindsAdapter = new MainFragmentGoodsKindsAdapter(mGoodsList);
        mVpGoodsKinds.setAdapter(mMainFragmentGoodsKindsAdapter);
    }

    ////新闻滚动ViewPager自动轮播
    private void autoScroll() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = mVpFrag.getCurrentItem();
                mVpFrag.setCurrentItem(currentItem + 1);
                mHandler.postDelayed(this, 3000);
            }
        }, 1000);
    }

    @Override
    public void onSucceed(int what, Response response) {
        Gson gson = new Gson();
        switch (what) {
            case 0:
                //猜你喜欢数据解析
                GoodsInfo goodsInfo = gson.fromJson((String) response.get(), GoodsInfo.class);
                List<GoodsInfo.ResultBean.GoodlistBean> goodlist = goodsInfo.getResult().getGoodlist();
                mDataList.addAll(goodlist);
                break;
            case 1:
                //电影列表数据解析
                FilmInfo filmInfo = gson.fromJson((String) response.get(), FilmInfo.class);
                List<FilmInfo.ResultBean> resultBeen = filmInfo.getResult();

                for (int i = 0; i < resultBeen.size(); i++) {
                    View view = View.inflate(getActivity(), R.layout.film_item, null);
                    SimpleDraweeView ivIcom = (SimpleDraweeView) view.findViewById(R.id.iv_filmICon);
                    TextView tvFilmName = (TextView) view.findViewById(R.id.tv_filmName);
                    TextView tvFilmCount = (TextView) view.findViewById(R.id.tv_filmCount);
                    Uri uri = Uri.parse(resultBeen.get(i).getImageUrl());
                    ivIcom.setImageURI(uri);

                    tvFilmName.setText(resultBeen.get(i).getFilmName());
                    tvFilmCount.setText(resultBeen.get(i).getGrade() + "分");
                    mLayoutFilm.addView(view);

                }
                break;
        }
    }

    @Override
    public void onFailed(int what, Response response) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_img:
                //扫描二维码
                Toast.makeText(getActivity(), "二维码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.location_lay:
                //跳转到定位cityactivity
                Toast.makeText(getActivity(), "城市页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inputLL:
                //跳转到定位搜索页面
                Toast.makeText(getActivity(), "搜索页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //下拉刷新子线程
    class MyAsyns extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mPullToRefreshListView.onRefreshComplete();
        }
    }

}
