package orange.com.mysurfaceviewdemo;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Point {
    private float radius;

    public Point(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public Point setRadius(float radius) {
        this.radius = radius;
        return this;
    }
}
