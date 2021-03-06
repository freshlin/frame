package com.example.freshlin.xl.frame.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.freshlin.xl.frame.BasePresenter;

/**
 * Created by xl on 2016/8/5.
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends BaseActivity {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();

    }

    protected abstract T initPresenter();

    protected abstract int initLayoutId();


    @Override
    protected void onResume() {
        super.onResume();

        presenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }



}
