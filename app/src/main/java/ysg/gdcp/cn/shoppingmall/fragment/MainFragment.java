package ysg.gdcp.cn.shoppingmall.fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import ysg.gdcp.cn.shoppingmall.Adapter.MainFragmentGoodsKindsAdapter;
import ysg.gdcp.cn.shoppingmall.Adapter.MainFragmentGridViewAdapter;
import ysg.gdcp.cn.shoppingmall.Adapter.MyMainFragmentAdapter;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.entity.HomeGoodsInfo;
import ysg.gdcp.cn.shoppingmall.view.Indicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


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
    private List<HomeGoodsInfo> mTowHomeGoodsInfos=new ArrayList<>();
    private MainFragmentGoodsKindsAdapter mMainFragmentGoodsKindsAdapter;
    private String[] mGoodsTitle;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mian, container, false);
        //新闻滚动ViewPager
        mVpFrag = (ViewPager) view.findViewById(R.id.vp_mainfrag);
        //新闻滚动ViewPager指示器
        mImdicator = (Indicator) view.findViewById(R.id.indicator);

        //商品分类viewpager
        mVpGoodsKinds = (ViewPager) view.findViewById(R.id.vp_goodskinds);
        //新闻滚动ViewPager适配器
        MyMainFragmentAdapter adapter = new MyMainFragmentAdapter(getActivity(), imgRes);


        //初始化数据
        initData();
        //初始化商品分类的GridView
        initGridView();

        mVpFrag.setAdapter(adapter);
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
        autoScroll();
        return view;

    }

    private void initData() {
        //获取GridView的标题资源
        mGoodsTitle = getResources().getStringArray(R.array.goods_title);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.goods_icon);
        for (int i = 0; i < mGoodsTitle.length; i++) {
            if(i<8){
                mOneHomeGoodsInfos.add(new HomeGoodsInfo(mGoodsTitle[i],typedArray.getResourceId(i,0)));
            }else {
                mTowHomeGoodsInfos.add(new HomeGoodsInfo(mGoodsTitle[i],typedArray.getResourceId(i,0)));
            }
        }



    }

    private void initGridView() {
        View view = View.inflate(getActivity(), R.layout.mainfragment_goods_item, null);
        GridView gvMainFragmentGoods = (GridView) view.findViewById(R.id.gv_mainfragment_goods);
        gvMainFragmentGoods.setAdapter(new MainFragmentGridViewAdapter(mOneHomeGoodsInfos,getActivity()));
        //第二页
        View viewT = View.inflate(getActivity(), R.layout.mainfragment_goods_item, null);
        GridView gvMainFragmentGoodsT = (GridView) viewT.findViewById(R.id.gv_mainfragment_goods);
        gvMainFragmentGoodsT.setAdapter(new MainFragmentGridViewAdapter(mTowHomeGoodsInfos,getActivity()));

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

}
