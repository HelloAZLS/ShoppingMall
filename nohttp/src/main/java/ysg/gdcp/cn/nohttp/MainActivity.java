package ysg.gdcp.cn.nohttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvContent = (TextView) findViewById(R.id.tv_content);

    }

    public void btn_get(View v) {
        String url = "http://www.baidu.com";
        //得到请求对列
        RequestQueue queue = NoHttp.getRequestQueueInstance();
        //创建请求消息
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);

        //将消息添加到对列
        /**
         * what :请求标识
         * request ：请求
         * response：请求回调监听
         */

        queue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                mTvContent.setText(response.get());
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    public void btn_post(View v) {
        String url = "http://www.baidu.com";
        RequestQueue queue = NoHttp.getRequestQueueInstance();
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);

        //增加头
//        request.addHeader()
        //增加主体
//        request.setDefineRequestBody()
        queue.add(1, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
