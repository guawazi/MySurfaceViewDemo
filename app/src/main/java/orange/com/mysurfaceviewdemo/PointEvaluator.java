package orange.com.mysurfaceviewdemo;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2016/10/20.
 */

public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float startValueRadius = startValue.getRadius();
        float endValueRadius = endValue.getRadius();
        float curValue = startValueRadius + (endValueRadius - startValueRadius) * fraction;
        return new Point(curValue);
    }
}
