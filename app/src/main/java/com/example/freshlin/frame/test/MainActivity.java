package com.example.freshlin.frame.test;

import android.os.Bundle;


import com.example.freshlin.frame.R;
import com.example.freshlin.frame.frame.BaseMVPActivity;

public class MainActivity extends BaseMVPActivity<MianView, MainPresenter> implements MianView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
