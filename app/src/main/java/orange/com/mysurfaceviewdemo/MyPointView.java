package orange.com.mysurfaceviewdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by Administrator on 2016/10/20.
 */

public class MyPointView extends View {
    private Paint mPaint;
    private Point mPoint;

    public MyPointView(Context context) {
        this(context, null);
    }

    public MyPointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPoint = new Point(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200, 200, mPoint.getRadius(), mPaint);
    }

    public void doAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "alpha", 1, 0.2f, 0.8f, 0.5f);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), new Point(20), new Point(60));
        objectAnimator.setDuration(3000);
        animator.setDuration(3000);
        animator.setInterpolator(new BounceInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point) animation.getAnimatedValue();
                invalidate(); // 强制刷新,调用onDraw()方法
            }
        });
        objectAnimator.start();
        animator.start();
    }
}
