package orange.com.mysurfaceviewdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PointActivity extends AppCompatActivity {

    private MyPointView myPointView;
    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        myPointView = (MyPointView) findViewById(R.id.myPointView);

        //通过PropertyValuesHolder对多个属性进行变化
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.4f, 2);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.4f, 2);
        //将这些变化的属性通过ofPropertyValuesHolder全部加入,再开始
        objectAnimator = ObjectAnimator.ofPropertyValuesHolder(myPointView,scaleX,scaleY);
        objectAnimator.setDuration(3000);
        AnimatorSet animatorSet = new AnimatorSet();
    }

    public void onClick(View v) {
        myPointView.doAnimation();
        objectAnimator.start();
    }
}
