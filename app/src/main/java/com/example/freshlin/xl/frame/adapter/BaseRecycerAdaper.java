package com.example.freshlin.xl.frame.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freshlin.xl.frame.ViewHolder.BaseViewHolder;
import com.example.freshlin.xl.frame.ViewHolder.BaseFooterHolder;
import com.example.freshlin.xl.frame.ViewHolder.BaseHeaderHolder;
import com.example.freshlin.xl.frame.listener.IAdapterListener;
import com.example.freshlin.xl.frame.listener.IFooterViewListner;
import com.example.freshlin.xl.frame.listener.IHeaderViewListner;

import java.util.List;

/**
 * Created by xl on 2016/8/15.
 */
public abstract class BaseRecycerAdaper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected final int UNUSE_TYPE = -2;
    protected final int HEADER_TYPE = -1;
    protected final int FOOTER_TYPE = 0;
    protected final int ADAPTER_TYPE = 1;

    private List<T> datas;

    private int headerId;

    private int footerId;

    protected LayoutInflater layoutInflater;

    private IAdapterListener adapterListener;

    protected IHeaderViewListner headerViewListner;

    protected IFooterViewListner footerViewListner;




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
        if(position == 0 && headerId != 0)
            type = HEADER_TYPE;
        else if(position == getItemCount() - 1 && footerId != 0)
            type = FOOTER_TYPE;
        else
            type = ADAPTER_TYPE;

        return type;
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(viewType == HEADER_TYPE) {
            View view = layoutInflater.inflate(headerId, null);
            return headerViewListner == null ? new BaseHeaderHolder(view) : headerViewListner.onCreateHeader(view);
        } else if(viewType == ADAPTER_TYPE)
            return  onCreateHolder(parent);
        else if(viewType == FOOTER_TYPE) {
            View view = layoutInflater.inflate(footerId, null);
            return footerViewListner == null ? new BaseFooterHolder(view) : footerViewListner.onCreateFooter(view);
        }
        return  null;
    }

    @Override
    public  void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        if(holder instanceof BaseHeaderHolder ){
            if(headerViewListner == null)
                return;
            headerViewListner.onBindHeaderData((BaseHeaderHolder) holder);
        }else if(holder instanceof BaseViewHolder){
            onBindData((BaseViewHolder)holder, position);
        }else{
            if(footerViewListner == null)
                return;
            footerViewListner.onBindFooterData((BaseFooterHolder) holder);
        }
    }

    protected abstract BaseViewHolder onCreateHolder(ViewGroup parent);
    protected abstract void onBindData(BaseViewHolder holder, int position);


    public BaseRecycerAdaper setHeaderId(int headerId){

        this.headerId = headerId;
        return this;
    }

    public BaseRecycerAdaper setFooterId(int footerId){
        this.footerId = footerId;
        return this;
    }

    public BaseRecycerAdaper setAdapterListner(IAdapterListener adapterListner){
        this.adapterListener = adapterListner;
        return this;
    }

    public BaseRecycerAdaper setHeaderViewListner(IHeaderViewListner headerViewListner){
        this.headerViewListner = headerViewListner;
        return this;
    }

    public BaseRecycerAdaper setFooterViewListner(IFooterViewListner footerViewListner){
        this.footerViewListner = footerViewListner;
        return this;
    }

    public IAdapterListener getAdapterListener() {
        return this.adapterListener;
    }
}
