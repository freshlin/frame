package com.example.freshlin.xl.frame.wigit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.freshlin.R;

/**
 * Created by xl on 2016/8/17.
 */
public class TestRefreshRecyclerView extends RefreshRecyClerView {

    public TestRefreshRecyclerView(Context context) {
        this(context, null);
    }

    public TestRefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public TestRefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View initTopView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.refresh_top, null);
    }

    @Override
    protected View initBottomView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.refresh_bottom, null);
    }

    @Override
    protected void pullDownState1(View pullDownView, int dx, int dy) {
        TextView tv = (TextView) pullDownView.findViewById(R.id.tvTop);
        tv.setText("测试下拉刷新");
    }

    @Override
    protected void pullDownStateDef1(View pullDownView, int cx, int cy) {
        TextView tv = (TextView) pullDownView.findViewById(R.id.tvTop);
        tv.setText("测试下拉刷新");
    }

    @Override
    protected void pullDownState2(View pullDownView, int dx, int dy) {
        TextView tv = (TextView) pullDownView.findViewById(R.id.tvTop);
        tv.setText("松开刷新。。。");
    }

    @Override
    protected void pullDownStateDef2(View pullDownView, int cx, int cy) {

    }

    @Override
    protected void pullDownState3(View pullDownView) {
        TextView tv = (TextView) pullDownView.findViewById(R.id.tvTop);
        tv.setText("加载中。。。");
    }


    @Override
    protected void pullDownStateDef3(View pullDownView, int cx, int cy) {

    }

    @Override
    protected void pullUpState1(View pullUpView, int dx, int dy) {
        TextView tv = (TextView)pullUpView.findViewById(R.id.tvBottom);
        tv.setText("测试上拉加载更多");
    }

    @Override
    protected void pullUpStateDef1(View pullUpView, int cx, int cy) {
        TextView tv = (TextView)pullUpView.findViewById(R.id.tvBottom);
        tv.setText("测试上拉加载更多");
    }

    @Override
    protected void pullUpState2(View pullUpView, int dx, int dy) {
        TextView tv = (TextView)pullUpView.findViewById(R.id.tvBottom);
        tv.setText("松开加载更多。。。");
    }

    @Override
    protected void pullUpStateDef2(View pullUpView, int cx, int cy) {

    }

    @Override
    protected void pullUpState3(View pullUpView) {
        TextView tv = (TextView)pullUpView.findViewById(R.id.tvBottom);
        tv.setText("加载中。。。");
    }
    @Override
    protected void pullUpStateDef3(View pullUpView, int cx, int cy) {

    }
}
