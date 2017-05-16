package ysg.gdcp.cn.shoppingmall.entity;

/**
 * Created by Administrator on 2017/5/16 16:06.
 *
 * @author ysg
 */

public class HomeGoodsInfo {
    private  String iconName;
    private int iconId;

    public HomeGoodsInfo(String iconName, int iconId) {
        this.iconName = iconName;
        this.iconId = iconId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
