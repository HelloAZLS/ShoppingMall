package ysg.gdcp.cn.shoppingmall.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

/**
 * Created by Administrator on 2017/5/16 10:44.
 *
 * @author ysg
 */

public class WaitDialog extends ProgressDialog {
    public WaitDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage("正在玩命加载中...");
    }
}
