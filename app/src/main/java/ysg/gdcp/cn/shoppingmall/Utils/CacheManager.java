package ysg.gdcp.cn.shoppingmall.Utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/5/24 08:42.
 * 此类用于对软件的缓存数据进行处理
 *
 * @author ysg
 */

public class CacheManager {
    /**
     * 清除、data/data/packagename/files下的内容·
     *
     * @param context
     */
    public static void cleanCacher(Context context) {
        deteleFileByDir(context.getCacheDir());
    }

    /**
     * 清楚应用数据库缓存
     *
     * @param context
     */
    public static void cleanDatabases(Context context) {
        deteleFileByDir(new File("data/data" + context.getPackageName() + "/databases"));
    }

    /**
     * 清除sp缓存
     *
     * @param context
     */
    public static void cleanSharedPreference(Context context) {
        deteleFileByDir(new File("data/data" + context.getPackageName() + "/config"));

    }

    /**
     * 更具数据库名称删除数据
     *
     * @param context
     * @param name
     */
    public static void cleanDataByName(Context context, String name) {
        context.deleteDatabase(name);
    }

    /**
     * 清除外部的缓存内容
     *
     * @param context
     */
    public static void cleanExternalCacher(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deteleFileByDir(context.getExternalCacheDir());
        }
    }

    /**
     * 清除自定的缓存数据
     *
     * @param filePath
     */
    public static void cleanCustomCache(String filePath) {
        deteleFileByDir(new File(filePath));
    }

    /**
     * 删除本应用中的数据
     *
     * @param context
     * @param fileParh
     */
    public static void cleanApplicationData(Context context, String... fileParh) {
        cleanCacher(context);
        cleanExternalCacher(context);
        cleanSharedPreference(context);
        if (fileParh == null) {
            return;
        }
        for (String f : fileParh) {
            cleanCustomCache(f);
        }
    }

    /**
     * 删除方法
     *
     * @param cacheDir
     */

    private static void deteleFileByDir(File cacheDir) {
        if (cacheDir != null && cacheDir.isDirectory() && cacheDir.exists()) {
            for (File file : cacheDir.listFiles()) {
                file.delete();
            }
        }
    }

    public static long getFoldSize(File file) {
        long fileSize = 0;

        try {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    fileSize = fileSize + getFoldSize(files[i]);
                } else {
                    fileSize = fileSize + files[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileSize;
    }

    public static void cleanFoldFile(String path, boolean deletePath) {
        if (!TextUtils.isEmpty(path)) {
            try {
                File file = new File(path);
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        cleanFoldFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deletePath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFormatSize(double size) {
        double kByte = size / 1024;
        if (kByte < 1) {
            return size + "byte";
        }
        double mByte = kByte / 1024;
        if (mByte < 1) {
            BigDecimal bigDecimal = new BigDecimal(Double.toString(kByte));
            return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
        }
        return null;

    }

    /**
     * 获取缓存大小
     *
     * @param file
     * @return
     */
    public static String getCacheSize(File file) {
        return getFormatSize(getFoldSize(file));
    }
}
