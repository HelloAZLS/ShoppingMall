package ysg.gdcp.cn.shoppingmall.Utils;

import android.util.Log;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import ysg.gdcp.cn.shoppingmall.entity.BaseModel;
import ysg.gdcp.cn.shoppingmall.entity.FavorInfo;
import ysg.gdcp.cn.shoppingmall.listener.MyBmobListener;


/**
 * Created by Administrator on 2017/5/25 07:44.
 *
 * @author ysg
 */

public class BmobManager {
    private String mID;
    private MyBmobListener mListener;

    private BmobManager() {

    }

    public void setListener(MyBmobListener listener) {
        this.mListener = listener;
    }

    private static BmobManager manager = null;

    public synchronized static BmobManager getInstance() {
        if (manager == null) {
            manager = new BmobManager();
        }
        return manager;
    }

    //插入数据
    public void insertData(BaseModel baseModel) {
        baseModel.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    mID = objectId;
                    Log.i("Bmob", "添加数据成功，返回objectId为：" + objectId);
                } else {
                    Log.i("Bmob", "添加数据失败，" + e.getMessage());
                }
            }
        });

    }

    //删除数据
    public void deteleData(final BaseModel model,String id) {
        model.setObjectId(id);
        model.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("Bmob", "删除成功:" + model.getUpdatedAt());
                } else {
                    Log.i("Bmob", "删除失败:" + e.getMessage());
                }
            }

        });

    }

    //更新数据
    public void upData() {

    }

    //更具id查询数据
    public void queryData(FavorInfo model, String id) {
        BmobQuery<FavorInfo> bmobQuery = new BmobQuery<FavorInfo>();
        bmobQuery.getObject(id, new QueryListener<FavorInfo>() {
            @Override
            public void done(FavorInfo model, BmobException e) {
                if (e == null) {
                    mListener.querySucess(model);
                } else {
                    mListener.queryFalure(e);
                }
            }
        });
    }

    public void queryAllData(FavorInfo favorInfo, boolean isQFa, String queryKey, boolean queryValue) {
        BmobQuery<FavorInfo> query = new BmobQuery<FavorInfo>();
        //查询playerName叫“比目”的数据
        if (isQFa) {
            query.addWhereEqualTo(queryKey, queryValue);
        }
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<FavorInfo>() {
            @Override
            public void done(List<FavorInfo> object, BmobException e) {
                if (e == null) {
                    mListener.queryAllSucess(object);
                } else {
                    mListener.queryAllFalure(e);
                }
            }
        });
    }

    //根据商品id查找商品
    public void queryGoodsId(FavorInfo favorInfo, final String gId) {
        BmobQuery<FavorInfo> query = new BmobQuery<FavorInfo>();
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<FavorInfo>() {
            @Override
            public void done(List<FavorInfo> object, BmobException e) {

                for (int i = 0; i < object.size(); i++) {
                    FavorInfo favorInfo = object.get(i);
                    if (favorInfo.getGoodsId().equals(gId)) {
                       mListener.queryGIdSUcess(favorInfo);
                    }

                }
            }
        });
    }
}
