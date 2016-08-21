package com.example.freshlin.xl.example.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freshlin.R;
import com.example.freshlin.xl.frame.ViewHolder.BaseViewHolder;
import com.example.freshlin.xl.frame.adapter.BaseDragSwipRecyerAdapter;
import com.example.freshlin.xl.frame.adapter.BaseRecycerAdaper;
import com.example.freshlin.xl.frame.adapter.ItemTouchHelperViewHolder;

import java.util.List;

/**
 * Created by xl on 2016/8/16.
 */
public class RefreshAdapter extends BaseDragSwipRecyerAdapter<String> {

    public RefreshAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected BaseViewHolder onCreateHolder(ViewGroup parent) {
        return new ItemHolder(layoutInflater.inflate(R.layout.item_base, null));
    }

    @Override
    protected void onBindData(final BaseViewHolder holder, int position) {

        ItemHolder h = (ItemHolder)holder;
        h.tvTest.setText(getItem(position));

        h.imTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    //itemTouchHelper.'(holder);
                    int xx = 1;
                    int yy = 2;
                }
                return false;
            }
        });
    }

    public class ItemHolder extends BaseViewHolder implements ItemTouchHelperViewHolder, View.OnClickListener {

        public TextView tvTest;
        public ImageView imTest;

        public ItemHolder(View itemView) {

            super(itemView);

            tvTest = (TextView) itemView.findViewById(R.id.tvTest);
            imTest = (ImageView)itemView.findViewById(R.id.imTest);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
            itemView.setAlpha(0.8f);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }

        @Override
        public void onClick(View v) {
            adapterListener.onItemClick(v, getAdapterPosition(), getItem(getAdapterPosition()));
        }
    }
}
