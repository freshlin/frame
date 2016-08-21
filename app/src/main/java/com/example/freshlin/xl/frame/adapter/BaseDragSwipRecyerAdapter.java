package com.example.freshlin.xl.frame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ViewGroup;

import com.example.freshlin.xl.frame.ViewHolder.BaseViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by freshlin on 2016/8/20.
 */
public abstract class BaseDragSwipRecyerAdapter<T> extends BaseRecycerAdaper<T> implements ItemTouchHelperAdapter{

    protected ItemTouchHelper itemTouchHelper;

    public BaseDragSwipRecyerAdapter(Context context, List<T> datas) {

        super(context, datas);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(this);
        itemTouchHelper = new ItemTouchHelper(callback);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(datas, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        if(getHeaderId() != 0)
            datas.remove(position -1);
        else
            datas.remove(position);

        notifyItemRemoved(position);
    }

    public void attachToRecyclerView(RecyclerView recyclerView){
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public int getHeaderId(){
        return headerId;
    }

    public int getFooterId(){
        return footerId;
    }



}
