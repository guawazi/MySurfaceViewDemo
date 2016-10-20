package orange.com.mysurfaceviewdemo;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2016/10/19.
 */

public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt + (startInt - endInt) * fraction);
        return ((char) curInt);
    }
}
