package com.example.freshlin.frame.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.freshlin.frame.R;
import com.example.freshlin.frame.frame.activity.BaseActivity;

/**
 * Created by freshlin on 2016/8/15.
 */
public class TestMyRefreshViewActivity extends BaseActivity{

    private RecyclerView myRecylerview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.act_test_refreshview;
    }


    @Override
    public void bindView() {
        hideToolBar();
        myRecylerview = (RecyclerView) findViewById(R.id.myRecylerview);


    }

    @Override
    public void bindData() {

    }
}
