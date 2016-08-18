package com.example.freshlin.xl.frame.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.freshlin.R;

/**
 * Created by xl on 2016/8/17.
 */
public abstract class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        this(context, R.style.customDialog);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.initLayoutId());
        initWindow();
        bindView();
        bindData();
    }

    protected void windowDeploy(float width, float height, int gravity) {
        Window window = this.getWindow();
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        if(width == 0.0F) {
            params.width = -2;
        } else if(width > 0.0F && width <= 1.0F) {
            params.width = (int)((float)this.getContext().getResources().getDisplayMetrics().widthPixels * width);
        } else {
            params.width = (int)width;
        }

        if(height == 0.0F) {
            params.height = -2;
        } else if(height > 0.0F && height <= 1.0F) {
            params.height = (int)((float)this.getContext().getResources().getDisplayMetrics().heightPixels * height);
        } else {
            params.height = (int)height;
        }

        params.verticalMargin = -0.1F;
        window.setAttributes(params);
        this.getWindow().setGravity(gravity);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }
    protected abstract int initLayoutId();

    protected abstract void initWindow();

    protected abstract void bindView();

    protected abstract void bindData();

}
