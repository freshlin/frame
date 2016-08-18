package com.example.freshlin.xl.frame.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.freshlin.BuildConfig;

/**
 * Created by xl on 2016/8/15.
 */
public class ToastUtils {

    private ToastUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = BuildConfig.DEBUG;


    public static void showShort(Context context, String message){
        if(isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, CharSequence message){
        if(isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context,int id){
        if(isShow)
            Toast.makeText(context, context.getString(id), Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String message){
        if(isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public static void showLong(Context context, CharSequence message){
        if(isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int id){
        if(isShow)
            Toast.makeText(context, context.getString(id), Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, String message, int duration){
        if(isShow)
            Toast.makeText(context, message, duration).show();
    }

    public static void show(Context context, CharSequence message, int duration){
        if(isShow)
            Toast.makeText(context, message, duration).show();
    }

    public static void show(Context context, int id, int duration){
        if(isShow)
            Toast.makeText(context, context.getString(id), duration).show();
    }

}
