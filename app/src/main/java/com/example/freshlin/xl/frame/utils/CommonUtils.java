package com.example.freshlin.xl.frame.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.List;

/**
 * Created by xl on 2016/8/7.
 */
public class CommonUtils {

    private static long lastTime;

    public static String getProccesName(Context context, int pid){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List runningApps = am.getRunningAppProcesses();

        if(runningApps == null)
            return null;
        ActivityManager.RunningAppProcessInfo processInfo;

        Iterator iterator = runningApps.iterator();
        do {
            if(!iterator.hasNext())
                return null;


            processInfo  = (ActivityManager.RunningAppProcessInfo) iterator.next();

        }while (pid != processInfo.pid);

        return processInfo.processName;
    }

    public static boolean isFastClick() {
        long curTime = System.currentTimeMillis();
        if(curTime - lastTime < 500L) {
            return true;
        } else {
            lastTime = curTime;
            return false;
        }
    }
}
