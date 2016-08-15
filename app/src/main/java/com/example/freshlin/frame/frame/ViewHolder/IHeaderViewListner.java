package com.example.freshlin.frame.frame.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by freshlin on 2016/8/15.
 */
public interface IHeaderViewListner {

    void onBindHeaderData(BaseHeaderHolder holder);

    BaseHeaderHolder onCreateHeader(View view);
}
