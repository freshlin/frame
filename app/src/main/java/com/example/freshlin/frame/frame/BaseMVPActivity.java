package com.example.freshlin.frame.frame;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.freshlin.frame.R;

/**
 * Created by freshlin on 2016/8/3.
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends AppCompatActivity{
    protected T prerenter;
    private int layoutId;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){

        prerenter = initPresenter();

        layoutId = initLayoutId();

        if(prerenter == null)
            throw new RuntimeException("Please init your Presenter");
        if(layoutId == 0)
            throw new RuntimeException("Please init your layoutId");

        setContentView(layoutId);

       this.toolbar = (Toolbar)this.findViewById(R.id.head_toolBar);

        if(toolbar == null){

        }



    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }



    protected abstract T initPresenter();

    protected abstract int initLayoutId();

    @Override
    protected void onPostResume() {
        super.onPostResume();
        prerenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        prerenter.dettach();
        super.onDestroy();
    }
}
