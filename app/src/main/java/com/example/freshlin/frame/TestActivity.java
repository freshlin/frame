package com.example.freshlin.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by freshlin on 2016/8/2.
 */
public class TestActivity extends AppCompatActivity implements View.OnTouchListener{

    private int activePointerId = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);

        findViewById(R.id.llyTest).setOnTouchListener(this);
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
}
