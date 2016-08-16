package com.example.freshlin.frame.frame.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.example.freshlin.frame.BuildConfig;

/**
 * Created by freshlin on 2016/8/16.
 */
public class DatabaseExportUtils {


    private final boolean DEBUG = BuildConfig.DEBUG;

    private final String TAG = DatabaseExportUtils.class.getSimpleName();

    private DatabaseExportUtils(){
         throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 开始导出数据 此操作比较耗时,建议在线程中进行
     *
     * @param context      上下文
     * @param targetFile   目标文件
     * @param databaseName 要拷贝的数据库文件名
     * @return 是否倒出成功
     */
    public boolean startExportDatabase(Context context, String targetFile,
                                       String databaseName) {
        if (DEBUG) {
            LogUtils.d(TAG, "start export ...");
        }
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {

            return false;
        }
        String sourceFilePath = Environment.getDataDirectory() + "/data/"
                + context.getPackageName() + "/databases/" + databaseName;
        String destFilePath = Environment.getExternalStorageDirectory()
                + (TextUtils.isEmpty(targetFile) ? (context.getPackageName() + ".db")
                : targetFile);
        boolean isCopySuccess = FileUtils
                .copyFile(sourceFilePath, destFilePath);
        if (DEBUG) {
            if (isCopySuccess) {
                LogUtils.d(TAG, "copy database file success. target file : "
                        + destFilePath);
            } else {
                LogUtils.w(TAG, "copy database file failure");
            }
        }
        return isCopySuccess;
    }
}
