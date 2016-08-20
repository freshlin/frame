package com.example.freshlin.xl.example.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.freshlin.R;
import com.example.freshlin.xl.example.dialog.CameraDialog;
import com.example.freshlin.xl.example.dialog.ShareDialog;
import com.example.freshlin.xl.frame.activity.BaseActivity;

/**
 * Created by freshlin on 2016/8/18.
 */
public class CameraActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.act_share;
    }

    @Override
    public void bindView() {
        findViewById(R.id.act_share_btnTest).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new CameraDialog(CameraActivity.this).show();
            }
        });
    }

    @Override
    public void bindData() {

    }
}
