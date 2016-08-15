package com.example.freshlin.frame.frame.wigit;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by freshlin on 2016/8/15.
 */
public class MyFrameLayout extends FrameLayout implements NestedScrollingParent {


    private int toolBarHeight;

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {

        int xx  = 1;
        int yy  = 2;
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        int xx = 1;
        int yy  = 2;
    }

    @Override
    public void onStopNestedScroll(View child) {

        int xx = 1;
        int yy  = 2;
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        int xx = 1;
        int yy  = 2;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if(dy < toolBarHeight) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
       return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
       return false;
    }



    public MyFrameLayout(Context context) {
        this(context, null);
    }

    public MyFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public MyFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int  size = getChildCount();
        View view = getChildAt(1);
        toolBarHeight = view.getMeasuredHeight();
        int xx = 1;
        int yy  = 2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
