package ysg.gdcp.cn.shoppingmall.Utils;

import android.app.Activity;
import android.util.Log;

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

    public BmobUtils(MyBmobListener listener) {
        mListener = listener;
    }

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
                    mListener.loginSucess(user);
                }
            }
        });
//        Toast.makeText(activity, "测试登录按钮", Toast.LENGTH_SHORT).show();
    }

    /**
     * Bmob账号登录
     *
     * @param userName 用户名
     * @param pwd      密码
     */

    public void countLogn(String userName, String pwd, final Activity activity) {
        BmobUser.loginByAccount(userName, pwd, new LogInListener<MyUser>() {

            @Override
            public void done(MyUser user, BmobException e) {
                if (user != null) {
//                    Toast.makeText(activity, "登录成功", Toast.LENGTH_SHORT).show();
                    mListener.loginSucess(user);
                } else {
                    Log.i("你好", e.getMessage());
                }
            }
        });
    }
}
