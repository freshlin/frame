package com.example.freshlin.frame.frame.wigit;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.example.freshlin.frame.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by freshlin on 2016/8/12.
 */
public class ShareView extends RelativeLayout implements View.OnClickListener{

    //一行view数量
    private final int number = 3;

    //子view（不包括取消view）
    private List<View> viewList = new ArrayList<View>();

    //子view中心点坐标（不包括取消view）
    private List<Point> viewPoint = new ArrayList<Point>();

    //子view数量（不包括取消view）
    private int childCount;

    //取消view
    private View cancelView;

    //布局宽度
    private int width;

    //布局高度
    private int height;

    //每行平均分布子view的宽度
    private int averageW;

    //子view行数
    private int lineNum;

    //取消按钮中心点X坐标
    private int cancelX;

    //取消按钮中心点Y坐标
    private int cancelY;

    public ShareView(Context context) {
        this(context, null);
    }

    public ShareView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);


        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int childH = getChildAt(0).getMeasuredHeight();

        childCount = getChildCount() - 1;

        int mo = childCount % number;

        lineNum = (number - (mo == 0 ? 3 : mo) + childCount) / number;
        height = (lineNum + 1) * childH;//100表示取消按钮的topMagin

        width = sizeWidth;

        averageW = width / number;


        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count =  getChildCount();
        for(int i = 0; i < count; ++i){

            View view = getChildAt(i);
            int childW = view.getMeasuredWidth();
            int childH = view.getMeasuredHeight();

            if(i != count - 1) {
                int left = (i % number) * averageW + averageW / 2 - childW / 2;
                int top  = (lineNum - 1 - i / number) * childH;

                view.layout(left, top, left + childW, top + childH);

                Point point = new Point(left + childW / 2, top + childH / 2);

                if(!viewPoint.contains(point))
                    viewPoint.add(point);

                if(!viewList.contains(view))
                    viewList.add(view);
            }
            else {
                view.layout(width / 2 - childW / 2, lineNum * childH, width / 2 + childW / 2, lineNum * childH + childH);
                cancelView = view;
                cancelX = width / 2;
                cancelY = lineNum * childH + childH / 2;

                view.setOnClickListener(this);
            }
        }


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.dlg_share_mine_ImCacel){
            animatonExit();
        }
    }

    public void animationEnter(){


        for(int i = childCount - 1; i >= 0; --i){

            float toX = cancelX - viewPoint.get(i).x;
            float toY = cancelY - viewPoint.get(i).y;

            float y = 30.0f;
            float x = (toX / toY) * y;

            TranslateAnimation translateAnimation = new TranslateAnimation(toX, -x,  toY, -y);
            translateAnimation.setDuration(250);
            translateAnimation.setFillAfter(true);

            ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            scaleAnimation.setDuration(250);


            TranslateAnimation translate= new TranslateAnimation(-x, 0,  -y, 0);
            translate.setStartOffset(250);
            translate.setDuration(100);
            translate.setInterpolator(new DecelerateInterpolator());
            translate.setFillAfter(true);

            AnimationSet animationSet = new AnimationSet(false);


            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(translate);

            viewList.get(i).startAnimation(animationSet);

        }
    }

    public void animatonExit(){

        long currentTime = System.currentTimeMillis();


        for(int i = childCount - 1; i >= 0; --i){

            int toX = cancelX - viewPoint.get(i).x;
            int toY = cancelY - viewPoint.get(i).y;

            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, toX,  Animation.ABSOLUTE, 0, Animation.ABSOLUTE, toY);

            // AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);


            AnimationSet animationSet = new AnimationSet(true);


            //animationSet.addAnimation(alphaAnimation);

            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(translateAnimation);


            animationSet.setDuration(250);
            animationSet.setFillAfter(true);



            viewList.get(i).startAnimation(animationSet);
        }

        cancelView.postDelayed(new Runnable() {
            @Override
            public void run() {
                RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(100);
                cancelView.startAnimation(rotateAnimation);
            }
        }, 250 + (System.currentTimeMillis() - currentTime));


    }
}
