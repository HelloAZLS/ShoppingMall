package ysg.gdcp.cn.shoppingmall.Utils;

import android.app.Activity;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import ysg.gdcp.cn.shoppingmall.entity.MyUser;
import ysg.gdcp.cn.shoppingmall.listener.MyBmobListener;

/**
 * Created by Administrator on 2017/5/20 11:17.
 *
 * @author ysg
 */

public class BmobUtils {
    private MyBmobListener mListener;

    /**
     * 获取手机验证码·方法
     *
     * @param phone 手机号码
     */
    public void getCode(String phone, Activity activity) {
        BmobSMS.requestSMSCode(phone, "团购", new QueryListener<Integer>() {

            @Override
            public void done(Integer smsId, BmobException ex) {
                if (ex == null) {//验证码发送成功

                    mListener.getCodeSucess();
                }
            }
        });
//        if (mListener != null) {
//            mListener.getCodeSucess();
//        }
//        Toast.makeText(activity, "测试获取验证码", Toast.LENGTH_SHORT).show();
    }

    /**
     * Bmob登录的方法
     *
     * @param phone 手机号
     * @param code  验证码
     */

    public void login(String phone, String code, Activity activity) {
        BmobUser.loginBySMSCode(phone, code, new LogInListener<MyUser>() {

            @Override
            public void done(MyUser user, BmobException e) {
                if (user != null) {
                    mListener.loginSucess();
                }
            }
        });
//        Toast.makeText(activity, "测试登录按钮", Toast.LENGTH_SHORT).show();
    }

}
