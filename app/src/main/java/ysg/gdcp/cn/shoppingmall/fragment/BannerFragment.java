package ysg.gdcp.cn.shoppingmall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ysg.gdcp.cn.shoppingmall.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends Fragment {


    private ImageView mIvBanner;
    private int mImageID;

    public BannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_banner, container, false);
        mIvBanner = (ImageView) view.findViewById(R.id.iv_banner);
        mIvBanner.setBackgroundResource(mImageID);

        return view;
    }

    public void setImageID(int imagId) {
        this.mImageID = imagId;
    }


}
