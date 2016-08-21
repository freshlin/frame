package com.example.freshlin.xl.example.activity;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.freshlin.R;
import com.example.freshlin.xl.frame.activity.BaseActivity;
import com.example.freshlin.xl.frame.wigit.TintImageView;

/**
 * Created by freshlin on 2016/8/19.
 */
public class PerformanceActivity extends BaseActivity implements ComponentCallbacks2{

    private ImageView imSelector;
    private TintImageView imTint;
    private ViewStub viewStub;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ItemTouchHelper itemTouchHelper;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.act_performance;
    }

    @Override
    public void bindView() {

        imSelector = (ImageView) findViewById(R.id.act_perfor_imSelector);
        imTint = (TintImageView) findViewById(R.id.act_perfor_imTint);
        viewStub = (ViewStub) findViewById(R.id.viewStub);

        imSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linearLayout == null)
                    return;
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });

        imTint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(viewStub.getVisibility() == View.GONE && linearLayout == null){
                    linearLayout = (LinearLayout) viewStub.inflate();
                }
                if(linearLayout != null){
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        int xx = 1;
        int yy  = 2;
        super.onTrimMemory(level);
    }

    @Override
    public void bindData() {
    }

}
