package com.example.freshlin.xl.frame.listener;

import android.view.View;

import com.example.freshlin.xl.frame.ViewHolder.BaseHeaderHolder;

/**
 * Created by xl on 2016/8/15.
 */
public interface IHeaderViewListner {

    void onBindHeaderData(BaseHeaderHolder holder);

    BaseHeaderHolder onCreateHeader(View view);
}
