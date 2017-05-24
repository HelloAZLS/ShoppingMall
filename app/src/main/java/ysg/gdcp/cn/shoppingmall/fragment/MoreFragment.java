package ysg.gdcp.cn.shoppingmall.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.Utils.CacheManager;

import static ysg.gdcp.cn.shoppingmall.R.id.tv_tel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(tv_tel)
    TextView mTvTel;
    @Bind(R.id.cache_size)
    TextView mCacheSize;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        mTitleTv.setText("更多");
        mTvTel.setText("138-8888-8888");
        mCacheSize.setText(CacheManager.getCacheSize(new File("data/data" + getActivity().getPackageName() + "/config")));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.clear_cache_layout, R.id.good_comment_layout, R.id.kefu_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_cache_layout:
                //清楚缓存
                CacheManager.cleanApplicationData(getActivity());
                Toast.makeText(getActivity(), "清除成功", Toast.LENGTH_SHORT).show();
                mCacheSize.setText("0kb");
                break;
            case R.id.good_comment_layout:
                Toast.makeText(getActivity(), "好评", Toast.LENGTH_SHORT).show();
                //好评
                break;
            case R.id.kefu_layout:
                //联系客服
//                Toast.makeText(getActivity(), "联系客服", Toast.LENGTH_SHORT).show();
                //跳转到打电话
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+mTitleTv.getText().toString().trim()));
                startActivity(intent);
                break;
        }
    }
}
