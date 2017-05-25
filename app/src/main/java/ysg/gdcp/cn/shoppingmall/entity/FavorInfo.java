package ysg.gdcp.cn.shoppingmall.entity;

/**
 * Created by Administrator on 2017/5/25 07:50.
 *
 * @author ysg
 */

public class FavorInfo extends BaseModel {

    private  String goodsId;



    private String propduct;
    private String price;
    private  String value;
    private  boolean isFavor;
    private  String short_title;
    private  String imageUrl;

    public String getPropduct() {
        return propduct;
    }

    public void setPropduct(String pr0pduct) {
        this.propduct = pr0pduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFavor() {
        return isFavor;
    }

    public void setFavor(boolean favor) {
        isFavor = favor;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
