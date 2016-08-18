package com.example.freshlin.xl.frame.listener;

import android.view.View;

import com.example.freshlin.xl.frame.ViewHolder.BaseFooterHolder;

/**
 * Created by xl on 2016/8/15.
 */
public interface IFooterViewListner {

    void onBindFooterData(BaseFooterHolder holder);

    BaseFooterHolder onCreateFooter(View view);
}
