package ysg.gdcp.cn.shoppingmall.listener;

import cn.bmob.v3.exception.BmobException;
import ysg.gdcp.cn.shoppingmall.entity.FavorInfo;
import ysg.gdcp.cn.shoppingmall.entity.MyUser;

/**
 * Created by Administrator on 2017/5/25 08:47.
 *
 * @author ysg
 */

public abstract class MyBmobQueryAllListener implements  MyBmobListener {
    @Override
    public void getCodeSucess() {

    }

    @Override
    public void loginSucess(MyUser user) {

    }

    @Override
    public void querySucess(FavorInfo favorInfo) {

    }

    @Override
    public void queryFalure(BmobException e) {

    }
    @Override
    public void queryGIdSUcess(FavorInfo favorInfo){

    }

}
