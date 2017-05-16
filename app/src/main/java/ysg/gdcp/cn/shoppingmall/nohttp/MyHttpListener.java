package ysg.gdcp.cn.shoppingmall.nohttp;

import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2017/5/16 10:39.
 *
 * @author ysg
 */

public interface  MyHttpListener {
    void onSucceed(int what, Response  response);
    void onFailed(int what, Response response);

}
