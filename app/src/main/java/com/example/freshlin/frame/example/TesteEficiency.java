package com.example.freshlin.frame.example;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.freshlin.frame.R;
import com.example.freshlin.frame.frame.activity.BaseActivity;

/** 效率测试
 * Created by freshlin on 2016/8/10.
 */
public class TesteEficiency extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayoutId() {
        return 0;
    }

    @Override
    public void bindView() {
        //testWH();
        testInflate();
    }

    @Override
    public void bindData() {

    }
    private void testInflate(){
        long currentTime =  System.currentTimeMillis();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.act_test, null);

        Log.e("测试：LayoutInflater.from，", String.valueOf(System.currentTimeMillis() - currentTime));
    }
    /**
     * 获取屏幕宽高测试
     */
    private void testWH(){

        //第一种
        long currentTime =  System.currentTimeMillis();
        for(int i = 0; i < 10000; ++i) {
            WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
        }

        Log.e("第一种方法：", String.valueOf(System.currentTimeMillis() - currentTime));//46

        currentTime =  System.currentTimeMillis();
        for(int i = 0; i < 10000; ++i) {
            Resources resources = this.getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            int width3 = dm.widthPixels;
            int height3 = dm.heightPixels;
        }

        Log.e("第二种方法：", String.valueOf(System.currentTimeMillis() - currentTime));//6


    }
}
