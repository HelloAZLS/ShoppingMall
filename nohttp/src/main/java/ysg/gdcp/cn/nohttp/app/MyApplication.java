package ysg.gdcp.cn.nohttp.app;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by Administrator on 2017/5/16 10:14.
 *
 * @author ysg
 */

public class  MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化nohttp
        NoHttp.initialize(this);
    }
}
