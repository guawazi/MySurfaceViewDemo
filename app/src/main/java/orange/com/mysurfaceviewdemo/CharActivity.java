package orange.com.mysurfaceviewdemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CharActivity extends AppCompatActivity {

    private ValueAnimator animator;
    private TextView tv_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);
        tv_textView = (TextView) findViewById(R.id.tv_textView);
        animator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char animatedValue = (char) animation.getAnimatedValue();
                tv_textView.setText(animatedValue + "");
            }
        });
    }
    public void onClick(View v){
        animator.start();
    }
}
