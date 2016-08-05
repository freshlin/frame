package com.example.freshlin.frame.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by freshlin on 2016/8/5.
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends BaseActivity{

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
        //MobclickAgent.onResume(context);
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }



}
