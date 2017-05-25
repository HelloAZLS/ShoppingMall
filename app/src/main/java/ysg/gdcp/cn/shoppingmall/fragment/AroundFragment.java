package ysg.gdcp.cn.shoppingmall.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.activity.LocationActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AroundFragment extends Fragment {



    @Bind(R.id.supplier_list_title_tv)
    TextView mListTitleTv;
    @Bind(R.id.supplier_list_cart_iv)
    ImageView mrListCartIv;
    @Bind(R.id.titlebar_layout)
    FrameLayout mTitlebarLayout;
    @Bind(R.id.supplier_list_product_tv)
    TextView mProductTv;
    @Bind(R.id.supplier_list_product)
    LinearLayout mSupplierListProduct;
    @Bind(R.id.supplier_list_sort_tv)
    TextView mSortTv;
    @Bind(R.id.supplier_list_sort)
    LinearLayout mSupplierListSort;
    @Bind(R.id.supplier_list_activity_tv)
    TextView mActivityTv;
    @Bind(R.id.supplier_list_activity)
    LinearLayout mSupplierListActivity;
    @Bind(R.id.supplier_list_lv)
    ListView mSupplierListLv;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.right_lay)
    LinearLayout mRightLay;
    @Bind(R.id.right_search)
    ImageView mRightSearch;
    private ArrayList<Map<String, String>> mData1;
    private ArrayList<Map<String, String>> mData2;
    private ArrayList<Map<String, String>> mData3;
    private ListView mlvAround;
    private PopupWindow mPopuWindow;
    private SimpleAdapter mAdapter;
    private SimpleAdapter mAdapter2;
    private SimpleAdapter mAdapter3;
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate  the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_around, container, false);
        ButterKnife.bind(this, view);
        initData();
        //初始化titlebar
        initTitleBar();
        initPop();
        return view;
    }

    private void initTitleBar() {
        mTitleTv.setVisibility(View.VISIBLE);
        mTitleTv.setText("周围");
        mRightLay.setVisibility(View.VISIBLE);
        mRightSearch.setVisibility(View.GONE);

    }

    private void initPop() {
        View view = View.inflate(getActivity(), R.layout.popwin_supplier_list, null);
        mlvAround = (ListView) view.findViewById(R.id.popwin_supplier_list_lv);
        mPopuWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mPopuWindow.setOutsideTouchable(true);
        mPopuWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopuWindow.setFocusable(true);
        mPopuWindow.setAnimationStyle(R.style.popwin_anim_style);
        mPopuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mProductTv.setTextColor(Color.parseColor("#5a5959"));
                mSortTv.setTextColor(Color.parseColor("#5a5959"));
                mActivityTv.setTextColor(Color.parseColor("#5a5959"));
            }
        });
        view.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopuWindow.dismiss();
            }
        });
        //给listVIew设置适配器
        mAdapter = new SimpleAdapter(getActivity(), mData1, R.layout.item_listview_popwin, new String[]{"name"}, new int[]{R.id.listview_popwind_tv});
        mAdapter2 = new SimpleAdapter(getActivity(), mData2, R.layout.item_listview_popwin, new String[]{"name"}, new int[]{R.id.listview_popwind_tv});
        mAdapter3 = new SimpleAdapter(getActivity(), mData3, R.layout.item_listview_popwin, new String[]{"name"}, new int[]{R.id.listview_popwind_tv});
        mlvAround.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPopuWindow.dismiss();
                switch (index) {
                    case 0:
                        String currentProduct = mData1.get(position).get("name");
                        mListTitleTv.setText(currentProduct);
                        mProductTv.setText(currentProduct);
                        break;
                    case 1:
                        String currentSort = mData2.get(position).get("name");
                        mListTitleTv.setText(currentSort);
                        mSortTv.setText(currentSort);
                        break;
                    case 2:
                        String currenAct = mData3.get(position).get("name");
                        mListTitleTv.setText(currenAct);
                        mActivityTv.setText(currenAct);
                        break;
                }
            }
        });

    }

    private void initData() {


        mData1 = new ArrayList<>();
        String[] menuStr1 = new String[]{"全部", "粮油", "衣服", "图书", "电子产品",
                "酒水饮料", "水果"};
        Map<String, String> map1;
        for (int i = 0, len = menuStr1.length; i < len; ++i) {
            map1 = new HashMap<String, String>();
            map1.put("name", menuStr1[i]);
            mData1.add(map1);
        }

        mData2 = new ArrayList<>();
        String[] menuStr2 = new String[]{"综合排序", "配送费最低"};
        Map<String, String> map2;
        for (int i = 0, len = menuStr2.length; i < len; ++i) {
            map2 = new HashMap<String, String>();
            map2.put("name", menuStr2[i]);
            mData2.add(map2);
        }

        mData3 = new ArrayList<>();
        String[] menuStr3 = new String[]{"优惠活动", "特价活动", "免配送费",
                "可在线支付"};
        Map<String, String> map3;
        for (int i = 0, len = menuStr3.length; i < len; ++i) {
            map3 = new HashMap<String, String>();
            map3.put("name", menuStr3[i]);
            mData3.add(map3);
        }
    }


    @OnClick({R.id.supplier_list_product, R.id.supplier_list_sort, R.id.supplier_list_activity, R.id.right_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.supplier_list_product:
                mProductTv.setTextColor(Color.parseColor("#39ac69"));
                mlvAround.setAdapter(mAdapter);
                mPopuWindow.showAsDropDown(mSupplierListProduct, 0, 2);
                index = 0;
                break;
            case R.id.supplier_list_sort:
                mSortTv.setTextColor(Color.parseColor("#39ac69"));
                mlvAround.setAdapter(mAdapter2);
                mPopuWindow.showAsDropDown(mSupplierListSort, 0, 2);
                index = 1;
                break;
            case R.id.supplier_list_activity:
                mActivityTv.setTextColor(Color.parseColor("#39ac69"));
                mlvAround.setAdapter(mAdapter3);
                mPopuWindow.showAsDropDown(mSupplierListActivity, 0, 2);
                index = 2;
                break;
            case R.id.right_lay:

                startActivity(new Intent(getActivity(), LocationActivity.class));
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
