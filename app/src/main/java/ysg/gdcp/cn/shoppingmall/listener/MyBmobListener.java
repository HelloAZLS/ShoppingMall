package ysg.gdcp.cn.shoppingmall.listener;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import ysg.gdcp.cn.shoppingmall.entity.FavorInfo;
import ysg.gdcp.cn.shoppingmall.entity.MyUser;

/**
 * Created by Administrator on 2017/5/20 11:23.
 *
 * @author ysg
 */

public interface MyBmobListener {
    //获取验证码成功
    void getCodeSucess();

    //登录成功
    void loginSucess(MyUser user);

    //查询成功
    void querySucess(FavorInfo favorInfo);

    //查询失败
    void queryFalure(BmobException e);
    //查询多条数据失败
    void queryAllFalure(BmobException e);
    //查询多条数据成功
    void queryAllSucess(List<FavorInfo> list);
    //根据1商品id查找商品成功
    void queryGIdSUcess(FavorInfo favorInfo);
}
