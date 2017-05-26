package ysg.gdcp.cn.shoppingmall.entity;


import android.net.Uri;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/5/20 11:26.
 *
 * @author ysg
 */

public class MyUser extends BmobUser {

    private String blance;
    private Uri imageUri;


    public MyUser(String blance, Uri imageUri) {

        this.blance = blance;
        this.imageUri = imageUri;
    }

    public MyUser() {
    }

    public String getBlance() {
        return blance;
    }

    public void setBlance(String blance) {
        this.blance = blance;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
