package ysg.gdcp.cn.shoppingmall.Utils;

import android.content.Context;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import ysg.gdcp.cn.shoppingmall.nohttp.HttpResponeseListener;
import ysg.gdcp.cn.shoppingmall.nohttp.MyHttpListener;

/**
 * Created by Administrator on 2017/5/16 10:56.
 *
 * @author ysg
 */

public class QueueUtils {
    private final RequestQueue mQueue;

    private QueueUtils() {
        mQueue = NoHttp.newRequestQueue();
    }

    private static QueueUtils queueUtils;

    public synchronized static QueueUtils getInstance() {
        if (queueUtils == null) {
            queueUtils = new QueueUtils();
        }
        return queueUtils;
    }

    public <T> void add(Context cotext, int what, Request<T> request,
                        MyHttpListener httpListener, boolean canCancel, boolean isLoading) {
        mQueue.add(what, request, new HttpResponeseListener<T>(cotext, request, httpListener, canCancel, isLoading));
    }
}
