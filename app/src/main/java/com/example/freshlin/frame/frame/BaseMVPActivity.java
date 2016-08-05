package com.example.freshlin.frame.frame;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.freshlin.frame.R;


/**
 * Created by freshlin on 2016/8/3.
 */
public abstract class BaseMVPActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements IBaseActivity{

    protected T prerenter;
    private int layoutId;
    private Toolbar toolbar;
    private FrameLayout flyMain;
    private boolean custumToolbar; //true 自定义，fasle layout_base默认的
    private boolean translucentStatus;

    private ImageView headLeft;
    private TextView headTitle;
    private RelativeLayout headRight;
    private TextView headRightTxt;
    private ImageView headRightIm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        initData();
    }

    private void init(){

        prerenter = initPresenter();

        layoutId = initLayoutId();

        setContentView(R.layout.layout_base);

        if(prerenter == null)
            throw new RuntimeException("Please init your Presenter");
        if(layoutId == 0)
            throw new RuntimeException("Please init your layoutId");

        //是否影响效率
        View view = LayoutInflater.from(this).inflate(layoutId, null);
        this.toolbar = (Toolbar)view.findViewById(R.id.head_toolBar);

        flyMain = (FrameLayout) findViewById(R.id.flyMain);

        if(toolbar == null){
            this.toolbar = (Toolbar)findViewById(R.id.head_toolBar);
        }else{
            custumToolbar = true;
            LinearLayout llyBase  = (LinearLayout) findViewById(R.id.llyBase);
            Toolbar defToolBar = (Toolbar)findViewById(R.id.head_toolBar);
            //是否影响效率
            llyBase.removeView(defToolBar);
        }

        int statusBarHeight = getStatusBarHeight();

        //状态栏与toolbar颜色相同
        if(this.translucentStatus && Build.VERSION.SDK_INT >= 19 && statusBarHeight > 0) {
            this.toolbar.setPadding(0, statusBarHeight, 0, 0);
            if(this.toolbar.getLayoutParams() != null) {
                this.toolbar.getLayoutParams().height = this.getResources().getDimensionPixelSize(R.dimen.toolbar_height) + statusBarHeight;
            }
        }

        //如果是默认toolbar
        if(!custumToolbar){
            headLeft = (ImageView)findViewById(R.id.head_left);
            headTitle = (TextView)findViewById(R.id.head_title);
            headRight = (RelativeLayout)findViewById(R.id.head_right);
            headRightIm = (ImageView)findViewById(R.id.head_imRight);
            headRightTxt = (TextView)findViewById(R.id.head_txtRight);
        }
        flyMain.addView(view);
    }

    /**
     * 设置返回图标
     * @param id
     * @return
     */
    protected BaseMVPActivity<V, T> buildHeadLeft(int id){

        if(id == 0 || custumToolbar)
            throw new RuntimeException("you are using custum toolbar!");

        headLeft.setImageResource(id);
        return this;
    }

    protected BaseMVPActivity<V, T> buildHeadTitle(String title){

        if(title == null || custumToolbar)
            throw new RuntimeException("you are using custum toolbar!");

        headTitle.setText(title);

        return this;
    }

    /**
     *  隐藏toolbar
     */
    protected void hideToolBar(){
        if(toolbar == null)
            return;
        this.toolbar.setVisibility(View.GONE);
    }

    /**
     * 获取t状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }



    protected abstract T initPresenter();

    protected abstract int initLayoutId();


    @Override
    protected void onPostResume() {
        super.onPostResume();
        prerenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        prerenter.dettach();
        super.onDestroy();
    }
}
