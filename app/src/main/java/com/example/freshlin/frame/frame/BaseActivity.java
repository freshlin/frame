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
public abstract class BaseActivity  extends AppCompatActivity implements IBaseActivity{
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
        bindView();
        bindData();
    }

    private void init(){
        layoutId = initLayoutId();

        setContentView(R.layout.layout_base);

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

        headLeft = (ImageView)findViewById(R.id.head_left);
        headTitle = (TextView)findViewById(R.id.head_title);
        headRight = (RelativeLayout)findViewById(R.id.head_right);
        headRightIm = (ImageView)findViewById(R.id.head_imRight);
        headRightTxt = (TextView)findViewById(R.id.head_txtRight);

        headLeft.setOnClickListener(onClickLisntner);

        flyMain.addView(view);
    }

    private View.OnClickListener onClickLisntner = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.head_left) onLeftClick(v);

            else if(v.getId() == R.id.head_right)onRightClick(v);
        }
    };

    /**
     * 左边点击事件
     * @param view
     */
    protected void onLeftClick(View view){
        finish();
    }

    /**
     * 右边点击事件
     * @param view
     */
    protected  void onRightClick(View view){

    }


    /**
     * 状态栏是否透明
     * @param translucentStatus
     */
    protected void setTranslucentStatus(boolean translucentStatus){
        this.translucentStatus = translucentStatus;
    }

    /**
     * 设置返回图标，标题
     * @param id
     * @param title
     */
    protected void setLeftAndTitle(int id, String title){
        if(id == 0 || title == null || custumToolbar)
            return;

        headLeft.setImageResource(id);

        headTitle.setText(title);
    }

    /**
     * 设置右边图标
     * @param visible
     * @param id
     */
    protected void setRight(boolean visible, int id){
        if(id == 0 || custumToolbar)
            return;

        headRight.setVisibility(visible ? View.VISIBLE : View.GONE);

        headRightIm.setImageResource(id);

        headRightTxt.setVisibility(View.GONE);
    }


    /**
     * 设置右边文字
     * @param visible
     * @param txt
     */
    protected void setRight(boolean visible, String txt){
        if(txt == null || custumToolbar)
            return;

        headRight.setVisibility(visible ? View.VISIBLE : View.GONE);

        headRightTxt.setText(txt);

        headRightIm.setVisibility(View.GONE);
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
    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * LayoutId
     * @return
     */
    protected abstract int initLayoutId();

}
