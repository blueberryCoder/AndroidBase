package com.blueberry.sample.module.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blueberry.sample.R;
import com.blueberry.sample.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by blueberry on 2016/8/9.
 */
public class AnimationActivity extends BaseActivity {
    @BindView(R.id.btn_property)
    Button btnProperty;
    @BindView(R.id.btn_tween)
    Button btnTween;
    @BindView(R.id.btn_frame)
    Button btnFrame;
    @BindView(R.id.iv_target)
    ImageView ivTarget;
    private static final String TAG = "AnimationActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animator);
        ButterKnife.bind(this);
        LinearLayout root =(LinearLayout) findViewById(R.id.root);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_item);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        root.setLayoutAnimation(controller);

//
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 200);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            /*一个Int 估值器*/
//            private IntEvaluator mEvaluator =new IntEvaluator();
//
//            @TargetApi(Builder.VERSION_CODES.ICE_CREAM_SANDWICH)
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                /*这里的值将从 0  --- 200*/
//                int currentValue = (int) animation.getAnimatedValue();
//                Log.i(TAG, "onAnimationUpdate: currentValue: " + currentValue);
//                float fraction = animation.getAnimatedFraction();
//                /*这里的值 将从 0---  1*/
//                Log.i(TAG, "onAnimationUpdate: fraction: " + fraction);
//
//                ViewGroup.LayoutParams layoutParams = ivTarget.getLayoutParams();
//                layoutParams.width = mEvaluator.evaluate(fraction,0,500);
//                ivTarget.setLayoutParams(layoutParams);
//            }
//        });
//        valueAnimator.setDuration(5000).start();
//
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivTarget, "x", 0, 300);
//        objectAnimator.setTarget(ivTarget);
//        objectAnimator.setDuration(2000);
//        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//        objectAnimator.start();

    }

    @OnClick({R.id.btn_property, R.id.btn_tween, R.id.btn_frame})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_property:
                Animator animator =
                        AnimatorInflater.loadAnimator(AnimationActivity.this, R.animator.animator);
                animator.setTarget(ivTarget);
                animator.start();

                AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(ivTarget, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(ivTarget, "rotationY", 0, 360),
                        ObjectAnimator.ofFloat(ivTarget, "rotation", 0, 360),
                        ObjectAnimator.ofFloat(ivTarget, "translationX", 0, 90),

                        ObjectAnimator.ofFloat(ivTarget, "alpha", 1, 0.25f, 1)
                );
                set.setDuration(5000).start();
                break;
            case R.id.btn_tween:

                Animation animation = AnimationUtils
                        .loadAnimation(AnimationActivity.this, R.anim.anim);
                ivTarget.startAnimation(animation);
                break;
            case R.id.btn_frame:
                ivTarget.setBackgroundDrawable(getResources().getDrawable(R.drawable.frame_anim));
                ((AnimationDrawable) ivTarget.getBackground()).start();
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_anim,R.anim.exit_anim);
    }
}
