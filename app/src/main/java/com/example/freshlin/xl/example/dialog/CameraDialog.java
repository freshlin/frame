package com.example.freshlin.xl.example.dialog;

import android.content.Context;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.freshlin.R;
import com.example.freshlin.xl.frame.dialog.BaseDialog;

/**
 * Created by freshlin on 2016/8/18.
 */
public class CameraDialog extends BaseDialog implements View.OnClickListener{
    public CameraDialog(Context context) {
        super(context);
    }

    public CameraDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dlog_camera;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        Window win = this.getWindow();
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        win.setWindowAnimations(R.style.dialogBottom);
        if(Build.VERSION.SDK_INT >= 21) {
            win.setAllowEnterTransitionOverlap(true);
            win.setAllowReturnTransitionOverlap(false);
        }
    }

    @Override
    protected void bindView() {
        findViewById(R.id.dlg_camera_btnTakePhoto).setOnClickListener(this);
        findViewById(R.id.dlg_camera_btnPhoto).setOnClickListener(this);
        findViewById(R.id.dlg_camera_btnCancel).setOnClickListener(this);
    }

    @Override
    protected void bindData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.dlg_camera_btnTakePhoto:
                break;
            case R.id.dlg_camera_btnPhoto:
                break;
            case R.id.dlg_camera_btnCancel:
                dismiss();
                break;
        }
    }
}
