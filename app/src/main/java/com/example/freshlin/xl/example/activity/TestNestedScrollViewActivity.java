package com.example.freshlin.xl.example.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;

import com.example.freshlin.R;
import com.example.freshlin.xl.frame.activity.BaseActivity;

/**
 * Created by xl on 2016/8/9.
 */
public class TestNestedScrollViewActivity extends BaseActivity{

    private NestedScrollView nestedScrollView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    protected int initLayoutId() {
        return R.layout.act_test2;
    }



    @Override
    public void bindView() {

        toolbar = getToolbar();
        toolbar.setAlpha(0);

        final int h = getResources().getDimensionPixelSize(R.dimen.test);
        final float value = 1.0f / h;

        nestedScrollView = (NestedScrollView)findViewById(R.id.nestedScrollView);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if(scrollY >= 0 && scrollY <= h){
                    toolbar.setAlpha(value * scrollY);
                }else{
                    toolbar.setAlpha(1);
                }
            }
        });
    }

    @Override
    public void bindData() {

    }
}
