package me.zhuao.android.sketch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import me.zhuao.android.sketch.R;

public class StepsBar extends LinearLayout {

    public static final int STEPS = 4;
    private View[] progressBar;
    private int currentStep = 1;
    private View[] circles;
    private LinearLayout textLayout;

    public StepsBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.progress_bar, this);
        findView();
    }

    public void nextStep() {
        if (currentStep == STEPS) {
            return;
        }
        int targetStep = currentStep + 1;
        playAnimationOnStep(targetStep);
        currentStep++;
    }

    private void playAnimationOnStep(int targetStep) {
        getTargetProgressBar().setVisibility(VISIBLE);
        getTargetProgressBar().startAnimation(bindAnimation(targetStep));

        int originalStep = targetStep - 1;
        getTargetCircle(originalStep).startAnimation(createOriginCycleZoomAnimation(originalStep));
        switchStepStatus(originalStep);
    }

    private void switchStepStatus(int originalStep) {
        View stepOverIndicator = getTargetCircle(originalStep).findViewWithTag(getResources().getString(R.string.step_bar_step_over_indicator_tag));
        View stepInIndicator = getTargetCircle(originalStep).findViewWithTag(getResources().getString(R.string.step_bar_step_in_indicator_tag));
        stepOverIndicator.setVisibility(VISIBLE);
        stepInIndicator.setVisibility(View.GONE);
    }

    private Animation createOriginCycleZoomAnimation(int originalStep) {
        final ScaleAnimation zoomAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        zoomAnimation.setInterpolator(new CycleInterpolator(0.5f));
        zoomAnimation.setDuration(300);
        zoomAnimation.setFillAfter(true);
        return zoomAnimation;
    }

    private ScaleAnimation bindAnimation(final int step) {
        ScaleAnimation growingAnimation = new ScaleAnimation(Math.max(0, (step - 2) / 3f), (step - 1) / 3f, 1, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        growingAnimation.setDuration(300);
        growingAnimation.setFillAfter(true);

        final ScaleAnimation zoomAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        zoomAnimation.setInterpolator(new CycleInterpolator(0.5f));
        zoomAnimation.setDuration(300);
        zoomAnimation.setFillAfter(true);

        growingAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                getTargetCircle(step).setVisibility(View.VISIBLE);
                getTargetCircle(step).startAnimation(zoomAnimation);

                View stepOverIndicator = getTargetCircle(step).findViewWithTag(getResources().getString(R.string.step_bar_step_over_indicator_tag));
                View stepInIndicator = getTargetCircle(step).findViewWithTag(getResources().getString(R.string.step_bar_step_in_indicator_tag));
                stepOverIndicator.setVisibility(GONE);
                stepInIndicator.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


        return growingAnimation;
    }

    private void findView() {
        progressBar = new View[STEPS];
        progressBar[0] = findViewById(R.id.steps_progress_bar);

        circles = new View[STEPS];
        circles[0] = findViewById(R.id.circle_step_1);
        circles[1] = findViewById(R.id.circle_step_2);
        circles[2] = findViewById(R.id.circle_step_3);
        circles[3] = findViewById(R.id.circle_step_4);

        textLayout = (LinearLayout) findViewById(R.id.steps_text);
    }

    private View getTargetProgressBar() {
        return progressBar[0];
    }

    private View getTargetCircle(int step) {
        return circles[step - 1];
    }

    private LinearLayout getTextView() {
        return textLayout;
    }

    public void slideText(int scrollY) {
        int textHeight = getResources().getDimensionPixelSize(R.dimen.steps_bar_height);
        if (scrollY <= textHeight * 2) {
            int topMargin = textHeight - scrollY / 2;
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getTextView().getLayoutParams();
            params.setMargins(getResources().getDimensionPixelSize(R.dimen.steps_Indicator_container_margin), topMargin, getResources().getDimensionPixelSize(R.dimen.steps_Indicator_container_margin), 0);
            getTextView().setLayoutParams(params);
        }
    }
}
