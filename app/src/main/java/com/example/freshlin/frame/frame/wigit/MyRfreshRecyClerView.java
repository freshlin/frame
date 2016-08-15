package com.example.freshlin.frame.frame.wigit;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by freshlin on 2016/8/15.
 */
public class MyRfreshRecyClerView extends ViewGroup implements NestedScrollingParent{

    private int sceenHeight;

    private int viewHeight;

    public MyRfreshRecyClerView(Context context) {
        this(context, null);
    }

    public MyRfreshRecyClerView(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public MyRfreshRecyClerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        DisplayMetrics displayMetrics =  getResources().getDisplayMetrics();

        sceenHeight = displayMetrics.heightPixels;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int height = getChildAt(0).getMeasuredHeight() + getChildAt(1).getMeasuredHeight() + getChildAt(2).getMeasuredHeight();

        viewHeight = height;

        setMeasuredDimension(sizeWidth, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i = 0; i < getChildCount(); ++i){
            View view = getChildAt(i);
            int childW = view.getMeasuredWidth();
            int childH = view.getMeasuredHeight();
            if(i == 0){
                view.layout(0, -childH, childW, 0);
            }else if(i == 1){
                view.layout(0, 0, childH, childH);
            }else{
                view.layout(0, viewHeight, childW, viewHeight + childH);
            }
        }
    }
}
