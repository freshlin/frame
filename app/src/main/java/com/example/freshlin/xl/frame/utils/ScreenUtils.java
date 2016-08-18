package com.example.freshlin.xl.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by xl on 2016/8/15.
 */
public class ScreenUtils {

    private ScreenUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 获取屏幕宽高
     * @param context
     * @return
     */
    public static Point sceenWidthHeight(Context context){
        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();

        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }


    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int statusbarHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 当前屏幕截屏，包含状态栏
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Point point = sceenWidthHeight(activity);
        int width = point.x;
        int height = point.y;
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 屏幕截屏，不包含状态栏
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        Point point = sceenWidthHeight(activity);
        int width = point.x;
        int height = point.y;
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }

}
