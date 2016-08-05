package com.example.freshlin.frame.test;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import com.example.freshlin.frame.R;
import com.example.freshlin.frame.frame.BaseMVPActivity;
import com.example.freshlin.frame.frame.IBaseActivity;

public class MainActivity extends BaseMVPActivity<MianView, MainPresenter> implements MianView ,IBaseActivity {

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
    public void initView() {
        //buildHeadLeft(R.mipmap.ico_back).build
    }

    @Override
    public void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
