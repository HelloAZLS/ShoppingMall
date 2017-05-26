package ysg.gdcp.cn.shoppingmall.event;


import android.net.Uri;


/**
 * Created by Administrator on 2017/5/26 08:05.
 *
 * @author ysg
 */

public class MessageEvent {
    public String userName;
    public Uri imageUri;
    public String blance;
    public MessageEvent() {
    }

    public MessageEvent(String userName, Uri imageUri, String blance) {
        this.userName = userName;
        this.imageUri = imageUri;
        this.blance = blance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getBlance() {
        return blance;
    }

    public void setBlance(String blance) {
        this.blance = blance;
    }
}
