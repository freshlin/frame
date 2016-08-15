package com.example.freshlin.frame.frame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freshlin.frame.frame.ViewHolder.BaseViewHolder;
import com.example.freshlin.frame.frame.ViewHolder.BaseFooterHolder;
import com.example.freshlin.frame.frame.ViewHolder.BaseHeaderHolder;
import com.example.freshlin.frame.frame.ViewHolder.IFooterViewListner;
import com.example.freshlin.frame.frame.ViewHolder.IHeaderViewListner;

import java.util.List;

/**
 * Created by freshlin on 2016/8/15.
 */
public abstract class BaseRecycerAdaper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected final int UNUSE_TYPE = -2;
    protected final int HEADER_TYPE = -1;
    protected final int FOOTER_TYPE = 0;
    protected final int ADAPTER_TYPE = 1;

    private List<T> datas;

    private int headerId;

    private int footerId;

    private LayoutInflater layoutInflater;

    private IFooterViewListner footerViewListner;

    private IHeaderViewListner headerViewListner;


    public BaseRecycerAdaper(Context context, List<T> datas) {

        super();

        this.datas = datas;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return datas.size() + (headerId != 0 ? 1 : 0) + (footerId != 0 ? 1 : 0);
    }



    public T getItem(int position){
        return  datas.get(position - (headerId != 0 ? 1 : 0));
    }


    @Override
    public int getItemViewType(int position) {
        int type = UNUSE_TYPE;

        if(position == 0){
            if(headerId != 0)
                type = HEADER_TYPE;
            else type = ADAPTER_TYPE;
        }else if(getItemCount() - 1 == position){
            type = FOOTER_TYPE;
        }else type = ADAPTER_TYPE;

        return type;
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(viewType == HEADER_TYPE) {
            View view = layoutInflater.inflate(headerId, parent);
            return headerViewListner == null ? new BaseHeaderHolder(view) : headerViewListner.onCreateHeader(view);
        } else if(viewType == ADAPTER_TYPE)
            return  onCreateHolder(parent);
        else if(viewType == FOOTER_TYPE) {
            View view = layoutInflater.inflate(footerId, parent);
            return footerViewListner == null ? new BaseFooterHolder(view) : footerViewListner.onCreateHeader(view);
        }
        return  null;
    }

    @Override
    public  void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        if(holder instanceof BaseHeaderHolder){
            headerViewListner.onBindHeaderData((BaseHeaderHolder) holder);
        }else if(holder instanceof BaseViewHolder){
            onBindData((BaseViewHolder)holder, position);
        }else{
           footerViewListner.onBindHeaderData((BaseFooterHolder) holder);
        }
    }

    protected abstract BaseViewHolder onCreateHolder(ViewGroup parent);
    protected abstract void onBindData(BaseViewHolder holder, int position);


    protected BaseRecycerAdaper setHeaderId(int headerId){

        this.headerId = headerId;
        return this;
    }

    protected BaseRecycerAdaper setFooterId(int footerId){
        this.footerId = footerId;
        return this;
    }

    protected BaseRecycerAdaper setHeaderViewListner(IHeaderViewListner headerViewListner){
        this.headerViewListner = headerViewListner;
        return this;
    }

    protected BaseRecycerAdaper setFooterViewListner(IFooterViewListner footerViewListner){
        this.footerViewListner = footerViewListner;
        return this;
    }
}
