package com.example.freshlin.xl.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.freshlin.R;
import com.example.freshlin.xl.frame.ViewHolder.BaseViewHolder;
import com.example.freshlin.xl.frame.adapter.BaseRecycerAdaper;

import java.util.List;

/**
 * Created by xl on 2016/8/16.
 */
public class RefreshAdapter extends BaseRecycerAdaper<String>{

    public RefreshAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected BaseViewHolder onCreateHolder(ViewGroup parent) {
        return new ItemHolder(layoutInflater.inflate(R.layout.item_base, null));
    }

    @Override
    protected void onBindData(BaseViewHolder holder, int position) {

            ItemHolder h = (ItemHolder)holder;
            h.tvTest.setText(getItem(position));
    }

    public class ItemHolder extends BaseViewHolder implements View.OnClickListener{

        public TextView tvTest;

        public ItemHolder(View itemView) {

            super(itemView);

            tvTest = (TextView) itemView.findViewById(R.id.tvTest);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                getAdapterListener().onItemClick(v, getAdapterPosition(), getItem(getAdapterPosition()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
