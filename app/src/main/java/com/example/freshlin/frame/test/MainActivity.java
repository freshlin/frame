package com.example.freshlin.frame.test;

import android.content.Context;
import android.os.Bundle;
import android.view.View;


import com.example.freshlin.frame.R;
import com.example.freshlin.frame.frame.BaseMVPActivity;
import com.example.freshlin.frame.frame.IBaseActivity;
import com.example.freshlin.frame.wigit.ImageTextButton;

public class MainActivity extends BaseMVPActivity<MianView, MainPresenter> implements MianView ,IBaseActivity, View.OnClickListener {

    private ImageTextButton imageTextButton;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (Context)this;

    }
    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void bindView() {
        //buildHeadLeft(R.mipmap.ico_back).build
        imageTextButton = (ImageTextButton)findViewById(R.id.itbtnTest);

        imageTextButton.setOnClickListener(this);

        int xx = 1;
        int yy = 2;
    }

    @Override
    public void bindData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {
        int xx = 1;
        int yy = 2;
        int zz = 3;
    }
}
