package com.example.freshlin.frame.example;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.freshlin.frame.R;
import com.example.freshlin.frame.frame.activity.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by freshlin on 2016/8/2.
 */
public class TestActivity extends BaseActivity implements View.OnTouchListener{

    private int activePointerId = -1;
    List<Integer> list = new ArrayList<Integer>();
    private final Handler mHandler = new MyHanlder(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for(int i = 0; i < 1024 * 100; ++i){
            list.add(i);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                mHandler.sendMessage(message);
            }
        }).start();
    }

    private static  class MyHanlder extends Handler{

        private final WeakReference<TestActivity> mActivity;

        public MyHanlder(TestActivity activity) {
            mActivity = new WeakReference<TestActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {

        }
    }


    @Override
    protected int initLayoutId() {
        return R.layout.act_test;
    }


    @Override
    public void bindView() {
        findViewById(R.id.llyTest).setOnTouchListener(this);
    }

    @Override
    public void bindData() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action  = MotionEventCompat.getActionMasked(event);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);
                // Save the ID of this pointer (for dragging)
                activePointerId = MotionEventCompat.getPointerId(event, 0);
                break;
            case MotionEvent.ACTION_MOVE:
                final int movePointerIndex = MotionEventCompat.getActionIndex(event);

                if(movePointerIndex != activePointerId){
                    for(int i = 0; i < 10 ; ++i) {
                        Log.e("lajfldsjlfjls", "11111111111111111111111");
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:

        }
        return true;
    }

    @Override
    protected void onDestroy() {
        Log.e("lajfldsjlfjls", "33333333333333333333333333333333333333333333333333333333333333");
        int xx = 1;
        int yy = 2;
        mHandler.removeCallbacksAndMessages(null);
        list.clear();
        super.onDestroy();

    }
}
