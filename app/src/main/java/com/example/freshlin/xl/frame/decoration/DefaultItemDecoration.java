package com.example.freshlin.xl.frame.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by xl on 2016/8/16.
 */
public class DefaultItemDecoration extends RecyclerView.ItemDecoration {


    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int GRID = 2;

    private Drawable drawable;
    private int type;



    public DefaultItemDecoration(Drawable drawable, int type) {
        this.drawable = drawable;
        this.type = type;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(type == HORIZONTAL){
            drawHorizontal(c, parent, state);
        }else if(type == VERTICAL){
            drawVertical(c, parent, state);
        }else if(type == GRID){
            drawGrideHorizontal(c, parent, state);
            drawGrideVertical(c, parent, state);
        }

    }

    /**
     * LineLayoutManager HORIZONTAL
     * @param c
     * @param parent
     * @param state
     */
    private void drawHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state){

        final int top = parent.getPaddingTop();

        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

    /**
     * LineLayoutManager Vertical
     * @param c
     * @param parent
     * @param state
     */
    private void drawVertical(Canvas c, RecyclerView parent,  RecyclerView.State state){
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for(int i = 0; i < childCount; ++i){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

    private void drawGrideVertical(Canvas c, RecyclerView parent, RecyclerView.State state){
        try {
            final int childCount = parent.getChildCount();
            for(int i = 0; i < childCount; ++i){
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
                final int left = child.getRight() + lp.rightMargin;
                final int right = left + drawable.getIntrinsicHeight();
                final int top = child.getTop() - lp.topMargin;
                final int bottom = child.getBottom() + lp.bottomMargin;
                drawable.setBounds(left, top, right, bottom);
                drawable.draw(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawGrideHorizontal(Canvas c, RecyclerView parent, RecyclerView.State state){
        try {
            final int childCount = parent.getChildCount();
            for(int i = 0; i < childCount; ++i){
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
                final int left = child.getLeft() - lp.leftMargin;
                final int right = child.getRight() + lp.rightMargin + drawable.getIntrinsicWidth();
                final int top = child.getBottom() + lp.bottomMargin;
                final int bottom = top + drawable.getIntrinsicHeight();
                drawable.setBounds(left, top, right,bottom);
                drawable.draw(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else
            {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (type == HORIZONTAL) {

            outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        } else if(type == VERTICAL){

            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
        }else if(type == GRID){

            try {
                int spanCount = getSpanCount(parent);
                int childCount = parent.getAdapter().getItemCount();

                if (isLastRaw(parent, parent.getChildAdapterPosition(view), spanCount, childCount)){// 如果是最后一行，则不需要绘制底部

                    outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
                } else if (isLastColum(parent, parent.getChildAdapterPosition(view), spanCount, childCount)){// 如果是最后一列，则不需要绘制右边

                    outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
                } else {

                    outRect.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
