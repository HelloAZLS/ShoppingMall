package ysg.gdcp.cn.shoppingmall.listener;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import ysg.gdcp.cn.shoppingmall.entity.FavorInfo;

/**
 * Created by Administrator on 2017/5/25 15:01.
 *
 * @author ysg
 */

public abstract class MyBmobQueryGIdListener implements  MyBmobListener {
    @Override
    public void getCodeSucess() {

    }

    @Override
    public void loginSucess() {

    }

    @Override
    public void querySucess(FavorInfo favorInfo) {

    }

    @Override
    public void queryFalure(BmobException e) {

    }

    @Override
    public void queryAllFalure(BmobException e) {

    }

    @Override
    public void queryAllSucess(List<FavorInfo> list) {

    }

}
