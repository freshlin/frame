package com.example.freshlin.frame.frame.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by freshlin on 2016/8/8.
 */
public class ShareHelper {

    private static  ShareHelper shareHelper;
    private SharedPreferences sharedPreferences;

    private ShareHelper(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(context.getPackageName(), 0);
    }

    public static ShareHelper getInstace(Context context){
        if(shareHelper == null) {
            synchronized (ShareHelper.class){
                if(shareHelper == null){
                    shareHelper = new ShareHelper(context);
                }
            }
        }
        return shareHelper;
    }


    public int getValue(String key, int def){
        return sharedPreferences.getInt(key, def);
    }

    public String getValue(String key, String def){
        return sharedPreferences.getString(key, def);
    }

    public boolean getValue(String key, boolean def){
        return sharedPreferences.getBoolean(key, def);
    }
    public float getValue(String key, float def){
        return sharedPreferences.getFloat(key, def);
    }


    public void putValue(String key, int value){
        sharedPreferences.edit().putInt(key, value);
    }

    public void putValue(String key, String value){
        sharedPreferences.edit().putString(key, value);
    }

    public void putValue(String key, boolean value){
        sharedPreferences.edit().putBoolean(key, value);
    }

    public void putValue(String key, float value){
        sharedPreferences.edit().putFloat(key, value);
    }


}
