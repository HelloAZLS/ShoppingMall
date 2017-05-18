package ysg.gdcp.cn.shoppingmall;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by Administrator on 2017/5/16 10:14.
 *
 * @author ysg
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化nohttp
        NoHttp.initialize(this);
        //Fresco图片加载框架初始化
        Fresco.initialize(this);
    }
}
