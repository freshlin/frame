package com.example.freshlin.xl.frame.wigit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.freshlin.R;

import java.lang.ref.WeakReference;

/**
 * Created by xl on 2016/7/30.
 */
public class ImageTextButton extends RelativeLayout {

    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    private WeakReference<Drawable> backgroudDrawable, imageDrawable;
    private String text;
    private int textSize;
    private int textColor;

    public ImageTextButton(Context context) {
        this(context, null);
    }

    public ImageTextButton(Context context, AttributeSet attrs) {
        this(context, attrs, 1);
    }

    public ImageTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,  AttributeSet attrs){

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ImageTextButton, 0, 0);
        if(a != null) {
            backgroudDrawable = new WeakReference<Drawable>(a.getDrawable(R.styleable.ImageTextButton_backGroundDrawable));
            imageDrawable = new WeakReference<Drawable>(a.getDrawable(R.styleable.ImageTextButton_imageDrawable));
            text = a.getString(R.styleable.ImageTextButton_text);
            textSize =  a.getDimensionPixelSize(R.styleable.ImageTextButton_textSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
            textColor = a.getColor(R.styleable.ImageTextButton_textColor, Color.BLACK);
            a.recycle();

            linearLayout = new LinearLayout(context);

            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            linearLayout.setLayoutParams(params);


            imageView = new ImageView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER_VERTICAL;
            imageView.setLayoutParams(lp);
            if(imageDrawable != null)
                imageView.setImageDrawable(imageDrawable.get());

            textView = new TextView(context);
            textView.setLayoutParams(lp);
            if(text != null)
                textView.setText(text);
            textView.setTextColor(textColor);
            textView.setTextSize(textSize);

            if(backgroudDrawable != null)
                this.setBackground(backgroudDrawable.get());

            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            backgroudDrawable.clear();
            imageDrawable.clear();
            imageView.getDrawable().setCallback(null);
            imageView = null;

            this.addView(linearLayout);
        }
    }

    public void callbackDrawable(){
        backgroudDrawable.clear();
        imageDrawable.clear();
        imageView.getDrawable().setCallback(null);
        imageView = null;
    }
}
