package com.example.freshlin.xl.example.mvpexp;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.freshlin.R;
import com.example.freshlin.xl.example.activity.CameraActivity;
import com.example.freshlin.xl.example.activity.PerformanceActivity;
import com.example.freshlin.xl.example.activity.ShareActivity;
import com.example.freshlin.xl.example.activity.TestActivity;
import com.example.freshlin.xl.example.activity.TestMyRefreshViewActivity;
import com.example.freshlin.xl.example.activity.TestNestedScrollViewActivity;
import com.example.freshlin.xl.example.activity.TestSetFactoryAct;
import com.example.freshlin.xl.example.activity.TesteEficiency;
import com.example.freshlin.xl.example.adapter.RefreshAdapter;
import com.example.freshlin.xl.example.bean.Act;
import com.example.freshlin.xl.frame.activity.BaseMVPActivity;
import com.example.freshlin.xl.frame.activity.IBaseActivity;
import com.example.freshlin.xl.frame.decoration.DefaultItemDecoration;
import com.example.freshlin.xl.frame.listener.IAdapterListener;
import com.example.freshlin.xl.frame.utils.ParseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMVPActivity<IMainView, MainPresenter> implements IMainView,IBaseActivity, IAdapterListener{

    private RecyclerView recyclerNavagation;

    private RefreshAdapter adapter;
    private List<String> datas = new ArrayList<String>();

    String className;
    String classInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean isTranslucentStatus() {
        return true;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.act_main;
    }
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void bindView() {

        recyclerNavagation =  (RecyclerView)findViewById(R.id.act_main_recyclerNavagation);
    }

    @Override
    public void bindData() {

        pullParse();

        datas.add("按钮点击（类似path效果）");
        datas.add("效率测试");
        datas.add("refeshRecyclerview(基于NestedScrollingParent实现)");
        datas.add("NestedScrollView测试");
        datas.add("测试换字体+ TextView跑马灯效果");
        datas.add("相机，相册");
        datas.add("性能优化");
        datas.add("其他测试");

        adapter = new RefreshAdapter(this, datas);
        adapter.setAdapterListner(this);
        recyclerNavagation.setAdapter(adapter);
        recyclerNavagation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerNavagation.addItemDecoration(new DefaultItemDecoration(getResources().getDrawable(R.drawable.divider_default), DefaultItemDecoration.VERTICAL));

        adapter.notifyDataSetChanged();
    }

    //pull 解析xml
    private void pullParse(){
        try {
            InputStream is =  getAssets().open("readme");
            List<Act>  list =  ParseUtils.parse(is);
            int xx = 1;
            int yy = 2;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onItemClick(View view, int position, Object item) {
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent.setClass(this, ShareActivity.class);
                break;
            case 1:
                intent.setClass(this, TesteEficiency.class);
                break;
            case 2:
                intent.setClass(this, TestMyRefreshViewActivity.class);
                break;
            case 3:
                intent.setClass(this, TestNestedScrollViewActivity.class);
                break;
            case 4:
                intent.setClass(this, TestSetFactoryAct.class);
                break;
            case 5:
                intent.setClass(this, CameraActivity.class);
                break;
            case 6:
                intent.setClass(this, PerformanceActivity.class);
                break;
            case 7:
                intent.setClass(this, TestActivity.class);
                break;
        }

        startActivity(intent);
    }
}
