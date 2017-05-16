package ysg.gdcp.cn.shoppingmall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import ysg.gdcp.cn.shoppingmall.R;
import ysg.gdcp.cn.shoppingmall.nohttp.MyHttpListener;
import ysg.gdcp.cn.shoppingmall.Utils.QueueUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AroundFragment extends Fragment implements MyHttpListener {


    public AroundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate  the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_around, container, false);
       view.findViewById(R.id.btn_around).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String url = "http://www.baidu.com";
               Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);

               QueueUtils.getInstance().add(getActivity(),0,request,AroundFragment.this,true,true);
           }
       });

        return view;
    }


    @Override
    public void onSucceed(int what, Response response) {
        switch (what) {
            case 0:
                Toast.makeText(getActivity(), ""+response.get(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onFailed(int what, Response response) {

    }
}
