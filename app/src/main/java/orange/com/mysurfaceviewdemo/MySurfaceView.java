package orange.com.mysurfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/10/18.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mSurfaceHolder;
    // 子线程标志位
    private boolean isDrawing;
    //画布
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;
    private float mLastX;
    private float mLastY;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this); //注册
        setFocusable(true); //可获取焦点
        setFocusableInTouchMode(true); //可通过触摸获取焦点
        setKeepScreenOn(true);  //保持屏幕常亮

        //创建画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStrokeWidth(10f);
        mPaint.setColor(Color.parseColor("#FF4081"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //路径
        mPath = new Path();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) { // 创建时调用
        isDrawing = true;
        new Thread(this).start(); //开启子线程用于绘制
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { //改变时调用

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { //销毁时调用
        isDrawing = false;
    }

    @Override
    public void run() {
        while (isDrawing) {
            drawing();
        }
    }

    private void drawing() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            //画画的逻辑 ...
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                //画东西
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
