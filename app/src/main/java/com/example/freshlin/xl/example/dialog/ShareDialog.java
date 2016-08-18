package com.example.freshlin.xl.example.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.freshlin.R;
import com.example.freshlin.xl.frame.dialog.BaseDialog;
import com.example.freshlin.xl.frame.wigit.ShareView;

/**
 * Created by xl on 2016/8/17.
 */
public class ShareDialog extends BaseDialog implements View.OnClickListener, ShareView.IExitAnimationEndListener{

    private ShareView shareView;
    private RelativeLayout rllyCacel;

    public ShareDialog(Context context) {
        super(context);
    }
    public ShareDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dialog_share;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        Window win = this.getWindow();
        win.setWindowAnimations(R.style.dialogFade);
    }

    @Override
    protected void bindView() {

        shareView = (ShareView) findViewById(R.id.myShare);

        rllyCacel = (RelativeLayout) findViewById(R.id.dlg_share_rllyCacel);

        rllyCacel.setOnClickListener(this);
        shareView.setAnimationEndListener(this);

    }

    @Override
    protected void bindData() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        shareView.animationEnter();
    }

    @Override
    public void onClick(View v) {
        shareView.animatonExit();
    }

    @Override
    public void animaitonEnd() {
        dismiss();
    }
}
