package com.example.freshlin.frame.frame.utils;

import android.util.Log;

import com.example.freshlin.frame.BuildConfig;

/**
 * Created by freshlin on 2016/8/15.
 */
public class LogUtils {

    private LogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static boolean isDebug = BuildConfig.DEBUG;

    private static final String TAG = "Debug:";

    public static void i(String msg){
        if(isDebug)
            Log.i(TAG, msg);
    }

    public static void i(String tag, String msg){
        if(isDebug)
            Log.i(tag, msg);
    }

    public static void e(String msg){
        if(isDebug)
            Log.e(TAG, msg);
    }
    public static void e(String tag, String msg){
        if(isDebug)
            Log.e(tag, msg);
    }

    public static void d(String msg){
        if(isDebug)
            Log.d(TAG, msg);
    }

    public static void d(String tag, String msg){
        if(isDebug)
            Log.d(tag, msg);
    }

    public static void v(String msg){
        if(isDebug)
            Log.v(TAG,msg);
    }

    public static void v(String tag, String msg){
        if(isDebug)
            Log.v(tag, msg);
    }

    public static void w(String msg){
        if(isDebug)
            Log.w(TAG,msg);
    }

    public static void w(String tag, String msg){
        if(isDebug)
            Log.w(tag, msg);
    }

}
