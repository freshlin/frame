package com.example.freshlin.xl.frame.wigit;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.NestedScrollingParent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.freshlin.xl.frame.adapter.BaseRecycerAdaper;
import com.example.freshlin.xl.frame.utils.ScreenUtils;


/**
 * Created by xl on 2016/8/15.
 */
public abstract class RfreshRecyClerView extends LinearLayout implements NestedScrollingParent{

    private int pullDownState = 0; // -3 -2  -1 0 1 2
    private int pullUpStae = 0;


    private int screenWidth;
    private int sceenHeight;

    private int viewHeight;

    private View topView;
    private View bottomView;
    private RecyclerView recyclerView;
    private boolean pullDown;
    private boolean pullUp;
    private Scroller scroller;
    private int touchSlop;


    public RfreshRecyClerView(Context context) {
        this(context, null);
    }

    public RfreshRecyClerView(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public RfreshRecyClerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        topView = initTopView();

        bottomView = initBottomView();

        recyclerView = new RecyclerView(context);

        DisplayMetrics displayMetrics =  getResources().getDisplayMetrics();

        Point point = ScreenUtils.sceenWidthHeight(context);
        screenWidth = point.x;
        sceenHeight = point.y;

        scroller = new Scroller(getContext());

        final ViewConfiguration configuration = ViewConfiguration.get(getContext());

        touchSlop = configuration.getScaledTouchSlop();

        this.addView(topView);
        this.addView(recyclerView);
        this.addView(bottomView);
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

        setMeasuredDimension(sizeWidth, sceenHeight);
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
                view.layout(0, 0, childW, sceenHeight);
            }else{
                view.layout(0, sceenHeight, childW, sceenHeight + childH);
            }
        }
    }





    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {

            int cx = scroller.getCurrX();

            int cy = scroller.getCurrY();

            if(pullDown){
                if(pullDownState == -1){
                    pullDownStateDef1(topView, cx, cy);
                }else if(pullDownState == -2){
                    pullDownStateDef2(topView, cx, cy);
                }else if(pullDownState == -3){
                    pullDownStateDef3(topView, cx, cy);
                }
            }

            if(pullUp){
                if(pullUpStae == -1){
                    pullUpStateDef1(bottomView, cx, cy);
                }else if(pullUpStae == -2){
                    pullUpStateDef2(bottomView, cx, cy);
                }else if(pullUpStae == -3){
                    pullUpStateDef3(bottomView, cx, cy);
                }
            }
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }else{
            if(scroller.isFinished()){
                if(pullDown){//下拉
                    if(pullDownState == -1){
                        pullDownState = 0;
                        pullDown = false;
                    }else if(pullDownState == -2){
                        pullDownState = 3;
                        pullDownState3(topView);
                        refreshListener.pullDownRefresh();

                    }else if(pullDownState == -3){
                        pullDownState = 0;
                        pullDown = false;
                    }
                }
                if(pullUp){//上拉
                    if(pullUpStae == -1){
                        pullUpStae = 0;
                        pullUp = false;
                    }else if(pullUpStae == -2) {
                        pullUpStae = 3;
                        pullUpState3(bottomView);
                        refreshListener.pullUpRefresh();
                    }else if(pullUpStae == -3){
                        pullUpStae = 0;
                        pullUp = false;
                    }
                }
            }
        }
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
    }

    @Override
    public void onStopNestedScroll(View child) {
        if(pullDown){
            if(pullDownState == 2 && getScrollY() < -topView.getMeasuredHeight()) {
                scroller.startScroll(0, getScrollY(), 0, -topView.getMeasuredHeight() - getScrollY(), 500);
                pullDownState = -2;}
            else if(pullDownState == 1 || getScrollY() >  -topView.getMeasuredHeight()) {
                scroller.startScroll(0, getScrollY(), 0, -getScrollY(), 500);
                pullDownState = -1;
            }
            postInvalidate();
        }
        else if(pullUp){
            if(pullUpStae == 2 && getScrollY() > bottomView.getMeasuredHeight()){
                scroller.startScroll(0, getScrollY(), 0, bottomView.getMeasuredHeight() - getScrollY(), 500);
                pullUpStae = -2;
            }else if(pullUpStae == 1 || getScrollY() < bottomView.getMeasuredHeight()){
                scroller.startScroll(0, getScrollY(), 0,   -getScrollY(), 500);
                pullUpStae = -1;
            }
            postInvalidate();
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int xx = 1;
        int yy  = 2;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

        if(!scroller.isFinished())
            return;

        int scrollY = getScrollY();

        LinearLayoutManager linearLayoutManager =  (LinearLayoutManager)recyclerView.getLayoutManager();

        int fisrtposition = linearLayoutManager .findFirstCompletelyVisibleItemPosition();
        int lastposition = linearLayoutManager.findLastCompletelyVisibleItemPosition();

        if(dy < 0  && scrollY <= 0 && fisrtposition == 0){
            pullDown = true;
            int ncy = 0;
            if(pullDownState == 3){
                scrollBy(0, dy / 5);
                ncy =  dy / 5;
            }else {
                if (scrollY < -topView.getMeasuredHeight()) {
                    pullDownState = 2;
                    scrollBy(0, dy / 5);
                    ncy = dy - dy / 5;
                    pullDownState2(topView, 0, dy / 5);
                } else {
                    scrollBy(0, dy);
                    pullDownState = 1;
                    pullDownState1(topView, 0, dy);
                }
            }
            consumed[1] = ncy;

        }else if(dy > 0 && scrollY >=0 && lastposition == recyclerView.getAdapter().getItemCount() - 1){
            pullUp = true;
            int ncy = 0;
            if(pullUpStae == 3){
                scrollBy(0, dy / 5);
                ncy =  dy / 5;
            }else {
                if (scrollY > bottomView.getMeasuredHeight()) {
                    pullUpStae = 2;
                    scrollBy(0, dy / 5);
                    ncy = dy - dy / 5;
                    pullUpState2(bottomView, 0, dy / 5);
                } else {
                    scrollBy(0, dy);
                    pullUpStae = 1;
                    pullUpState1(bottomView, 0, dy);
                }
            }
            consumed[1] = ncy;
        }
        else if((scrollY < 0 && dy > 0) || (scrollY > 0 && dy < 0)){
            if(pullDown){
                if(getScrollY() > -topView.getMeasuredHeight()){
                    pullDownStateDef1(topView, 0, dy);
                }
            }else if(pullUp){
                if(getScrollY() < bottomView.getMeasuredHeight()){
                    pullUpStateDef1(bottomView, 0, dy);
                }
            }
            scrollBy(0, dy);
            consumed[1] = dy;
        }

    }



    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return consumed;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if(getScrollY() > 0 && velocityY  < 0)
            return true;
        if(getScrollY() < 0 && velocityY > 0)
            return true;

        return false;

    }


    protected abstract View initTopView();

    protected abstract View initBottomView();

    protected abstract void pullDownState1(View pullDownView,int dx,  int dy);
    protected abstract void pullDownStateDef1(View pullDownView, int cx, int cy);
    protected abstract void pullDownState2(View pullDownView,int dx,  int dy);
    protected abstract void pullDownStateDef2(View pullDownView, int cx, int cy);
    protected abstract void pullDownState3(View pullDownView);
    protected abstract void pullDownStateDef3(View pullDownView, int cx, int cy);


    protected abstract void pullUpState1(View pullUpView,int dx,  int dy);
    protected abstract void pullUpStateDef1(View pullUpView, int cx, int cy);
    protected abstract void pullUpState2(View pullUpView,int dx,  int dy);
    protected abstract void pullUpStateDef2(View pullUpView, int cx, int cy);
    protected abstract void pullUpState3(View pullUpView);
    protected abstract void pullUpStateDef3(View pullUpView, int cx, int cy);


    public static interface RefreshListener{

        void pullDownRefresh();
        void pullUpRefresh();
    }

    private RefreshListener refreshListener;


    public void setRefreshListener(RefreshListener refreshListener){
        this.refreshListener = refreshListener;
    }


    public void refreshEnd(){
        if(pullDown){
            pullDownState = -3;
        }
        if(pullUp){
            pullUpStae = -3;
        }

        scroller.startScroll(0, getScrollY(), 0, -getScrollY(), 500);
        postInvalidate();
    }



    public void setAdapter(BaseRecycerAdaper adapter){
        recyclerView.setAdapter(adapter);
    }
    public void setLayoutManager(LinearLayoutManager layoutManager){
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration){
        recyclerView.addItemDecoration(itemDecoration);
    }
}
