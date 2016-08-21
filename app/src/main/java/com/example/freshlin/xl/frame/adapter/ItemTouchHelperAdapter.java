package com.example.freshlin.xl.frame.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by freshlin on 2016/8/20.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
