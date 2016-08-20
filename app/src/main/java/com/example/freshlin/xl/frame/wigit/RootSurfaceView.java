package com.example.freshlin.xl.frame.wigit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.freshlin.R;
import com.example.freshlin.xl.frame.utils.ScreenUtils;

/**
 * Created by freshlin on 2016/8/19.
 */
public class RootSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private float viewWidth;
    private float viewHeight;
    private int resourceId;
    private SurfaceHolder surfaceHolder;
    private Context context;
    private volatile boolean isRunning = false;

    public RootSurfaceView(Context context) {
        this(context, null);
    }

    public RootSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RootSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RootSurfaceView, 0, 0);
        int n = a.getIndexCount();
        Point point = ScreenUtils.sceenWidthHeight(context);
        viewWidth = point.x;
        viewHeight = point.y;
        for(int index=0; index<n; index++){
            int attr = a.getIndex(index);
            switch(attr){
                case R.styleable.RootSurfaceView_backResouce:{
                    resourceId = a.getResourceId(attr, 0);
                }
                break;
                case R.styleable.RootSurfaceView_view_width:{
                    viewWidth = a.getDimension(attr, point.x);
                }
                break;
                case R.styleable.RootSurfaceView_view_height:{
                    viewHeight = a.getDimension(attr, point.y);
                }
                break;
                default:{

                }
                break;
            }
        }
        a.recycle();
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
    }

    private Bitmap getDrawBitmap(Context context, float width, float height) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        Bitmap resultBitmap = zoomImage(bitmap, width, height);
        return resultBitmap;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawBackGround(holder);
        isRunning = false;
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void run() {
        while(isRunning){
            synchronized (surfaceHolder) {
                if(!surfaceHolder.getSurface().isValid()){
                    continue;
                }
                drawBackGround(surfaceHolder);
            }
            isRunning = false;
            break;
        }
    }

    private void drawBackGround(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        Bitmap bitmap = getDrawBitmap(context, viewWidth, viewHeight);
        canvas.drawBitmap(bitmap, 0, 0, null);
        bitmap.recycle();
        holder.unlockCanvasAndPost(canvas);
    }

    public static Bitmap zoomImage( Bitmap bgimage , float newWidth , float newHeight ) {
        float width = bgimage.getWidth( );
        float height = bgimage.getHeight( );
        Matrix matrix = new Matrix();
        float scaleWidth = newWidth/width;
        float scaleHeight = newHeight/height;
        matrix.postScale( scaleWidth, scaleHeight );
        Bitmap bitmap = Bitmap.createBitmap( bgimage, 0, 0, ( int ) width , ( int ) height, matrix, true );
        if( bitmap != bgimage ){
            bgimage.recycle();
            bgimage = null;
        }
        return bitmap;
    }
}
