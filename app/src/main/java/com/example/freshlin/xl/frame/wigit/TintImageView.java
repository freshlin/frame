package com.example.freshlin.xl.frame.wigit;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.freshlin.R;

/**
 * Created by xl on 2016/8/18.
 */
public class TintImageView extends ImageView{

    private ColorStateList colorStateList;

    public TintImageView(Context context) {
        this(context, null);
    }

    public TintImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TintImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TintImageView, 0, 0);

        colorStateList = a.getColorStateList(R.styleable.TintImageView_drawableTint);

        a.recycle();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(colorStateList != null)
            updateTintColor();
    }

    private void updateTintColor() {
        int color = this.colorStateList.getColorForState(this.getDrawableState(), 0);
        this.setColorFilter(color);
    }

    public void setColorStateList(ColorStateList colorStateList) {
        this.colorStateList = colorStateList;
        this.setColorFilter(this.colorStateList.getColorForState(this.getDrawableState(), 0));
    }
}
