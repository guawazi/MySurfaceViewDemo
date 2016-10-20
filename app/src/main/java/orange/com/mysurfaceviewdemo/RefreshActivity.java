package orange.com.mysurfaceviewdemo;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class RefreshActivity extends AppCompatActivity {

    private StoreHouseHeader header;
    private PtrFrameLayout store_house_ptr_frame;
    private TextView tv_interpolator;
    private Button btn_interpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        store_house_ptr_frame = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
        tv_interpolator = (TextView) findViewById(R.id.tv_interpolator);
        btn_interpolator = (Button) findViewById(R.id.btn_interpolator);
//        header = new StoreHouseHeader(this);
//        header.setPadding(0, 15, 0, 0);
//        header.initWithString("LiJiaJia");

        final View view = LayoutInflater.from(this).inflate(R.layout.custom_animation, null);
        store_house_ptr_frame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        store_house_ptr_frame.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        store_house_ptr_frame.setHeaderView(view);
        store_house_ptr_frame.addPtrUIHandler(new PtrUIHandler() {
            private View imageView;
            private Animation animation1;

            @Override
            public void onUIReset(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
                imageView = view.findViewById(R.id.anim_bg);
                animation1 = AnimationUtils.loadAnimation(RefreshActivity.this, R.anim.translate);
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {
                imageView.startAnimation(animation1);
            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
                imageView.startAnimation(animation1);

            }
        });
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                tv_interpolator.layout(tv_interpolator.getLeft(), value, tv_interpolator.getRight(), value + tv_interpolator.getHeight());
            }
        });
        btn_interpolator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator.start();
            }
        });
    }
}
