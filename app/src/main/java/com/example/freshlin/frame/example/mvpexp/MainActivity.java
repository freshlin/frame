package com.example.freshlin.frame.example.mvpexp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.freshlin.frame.R;
import com.example.freshlin.frame.example.TestMyRefreshViewActivity;
import com.example.freshlin.frame.frame.activity.BaseMVPActivity;
import com.example.freshlin.frame.frame.activity.IBaseActivity;
import com.example.freshlin.frame.frame.wigit.ImageTextButton;

public class MainActivity extends BaseMVPActivity<IMainView, MainPresenter> implements IMainView,IBaseActivity, View.OnClickListener {

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
//
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

        //startActivity(new Intent(this, TestNestedScrollViewActivity.class));
        //startActivity(new Intent(this, TesteEficiency.class));
        //startActivity(new Intent(this, TestPathActivity.class));
        startActivity(new Intent(this, TestMyRefreshViewActivity.class));

    }

    @Override
    protected void onDestroy() {
        imageTextButton.callbackDrawable();
        super.onDestroy();
    }
}