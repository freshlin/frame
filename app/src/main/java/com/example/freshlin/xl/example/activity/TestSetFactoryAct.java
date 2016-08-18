package com.example.freshlin.xl.example.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.freshlin.R;
import com.example.freshlin.xl.frame.activity.BaseActivity;

/**
 * Created by xl on 2016/8/18.
 */
public class TestSetFactoryAct extends BaseActivity{

    public static Typeface typeface;

    private TextView txtTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //如果要换App所有文字的字体，这部分code可写在BaseActivity中
        if (typeface == null) {
            typeface = Typeface.createFromAsset(getAssets(), "fonts/test2.TTF");
        }
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();

                View view = delegate.createView(parent, name, context, attrs);

                if ( view!= null && (view instanceof TextView)) {
                    if(typeface != null)
                    ((TextView) view).setTypeface(typeface);
                }
                return view;
            }
        });
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.act_test_setfactory;
    }


    @Override
    public void bindView() {
        txtTest = (TextView) findViewById(R.id.txtTest);
        txtTest.requestFocus();
    }

    @Override
    public void bindData() {

    }
}
