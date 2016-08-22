package com.example.freshlin.xl.example.mvpexp;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.example.freshlin.xl.frame.adapter.SimpleItemTouchHelperCallback;
import com.example.freshlin.xl.frame.decoration.DefaultItemDecoration;
import com.example.freshlin.xl.frame.listener.IAdapterListener;
import com.example.freshlin.xl.frame.utils.ParseUtils;
import com.example.freshlin.xl.frame.utils.SDCardUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
        test();

        datas.add("按钮点击（类似path效果）");
        datas.add("效率测试");
        datas.add("refeshRecyclerview(基于NestedScrollingParent实现)");
        datas.add("NestedScrollView测试");
        datas.add("测试换字体+ TextView跑马灯效果");
        datas.add("相机，相册");
        datas.add("性能优化");
        datas.add("其他测试");

        adapter = new RefreshAdapter(this, datas);
        adapter.attachToRecyclerView(recyclerNavagation);
        adapter.setAdapterListner(this);
        recyclerNavagation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerNavagation.addItemDecoration(new DefaultItemDecoration(getResources().getDrawable(R.drawable.divider_default), DefaultItemDecoration.VERTICAL));
        recyclerNavagation.setAdapter(adapter);
       // recyclerNavagation.setLayoutManager(new GridLayoutManager(this, 4));

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


    private static final String CATCH_PATH = Environment.getDownloadCacheDirectory().getPath()+ "/qr";
    private static final String DATA_PATH = Environment.getDataDirectory().getPath() + "/qr";


    private void test() {

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        //String s = filePath + "qr";
        String s = DATA_PATH;

        File dir = new File(s);
        dir.mkdir();


        File file = new File(s, System.currentTimeMillis() + ".png");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ico_mine_wechart);

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
