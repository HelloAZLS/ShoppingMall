package ysg.gdcp.cn.shoppingmall.nohttp;

import android.content.Context;
import android.content.DialogInterface;

import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import ysg.gdcp.cn.shoppingmall.view.WaitDialog;

/**
 * Created by Administrator on 2017/5/16 10:38.
 *
 * @author ysg
 */

public class HttpResponeseListener<T> implements OnResponseListener<T> {

    private Context mContext;
    private Request<T> mRequest;
    private MyHttpListener mListener;
    private WaitDialog mWaitDialog;
    private  boolean isLoading;

    public HttpResponeseListener(Context context, Request<T> request, MyHttpListener listener, boolean canCancel, boolean isLoading) {
        mContext = context;
        mRequest = request;
        mListener = listener;
        this.isLoading =isLoading;
        if (mContext != null) {
            mWaitDialog = new WaitDialog(mContext);
            mWaitDialog.setCancelable(canCancel);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mWaitDialog.cancel();
                }
            });
        }
    }

    @Override
    public void onStart(int what) {
        if (mWaitDialog != null&&isLoading&&!mWaitDialog.isShowing()) {
            mWaitDialog.show();
        }

    }

    @Override
    public void onSucceed(int what, Response<T> response) {
        if (mListener != null) {
            mListener.onSucceed(what, response);
        }
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        if (mListener != null) {
            mListener.onFailed(what, response);
        }
    }

    @Override
    public void onFinish(int what) {
        if (mWaitDialog != null&&isLoading&&mWaitDialog.isShowing()) {
            mWaitDialog.cancel();
        }
    }
}
