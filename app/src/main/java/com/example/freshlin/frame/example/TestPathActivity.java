package com.example.freshlin.frame.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.freshlin.frame.R;
import com.example.freshlin.frame.frame.activity.BaseActivity;
import com.example.freshlin.frame.frame.wigit.ShareView;

/**
 * Created by freshlin on 2016/8/12.
 */
public class TestPathActivity extends BaseActivity{

    private ShareView shareView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int initLayoutId() {
        return R.layout.act_test_path;
    }

    @Override
    public void bindView() {

         shareView = (ShareView)findViewById(R.id.myPath);

        findViewById(R.id.btnTestEnter).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shareView.animationEnter();
            }
        });
    }

    @Override
    public void bindData() {
    }

}
