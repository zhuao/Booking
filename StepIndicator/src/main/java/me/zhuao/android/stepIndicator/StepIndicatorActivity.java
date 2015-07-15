package me.zhuao.android.stepIndicator;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import me.zhuao.android.stepIndicator.view.StepsBar;

public class StepIndicatorActivity extends Activity{

    private StepsBar stepsBar;

    ScrollView scrollView;

    private float mPreviousY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_indicator_activity);
        stepsBar = ((StepsBar) findViewById(R.id.step_indicator_bar));
        scrollView = (ScrollView) findViewById(R.id.step_indicator_scroll_view);
        addScrollListener();
    }

    private void addScrollListener() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int scrollY = v.getScrollY();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mPreviousY = scrollY;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.requestFocusFromTouch();
                    stepsBar.slideText(Float.valueOf(Math.abs(mPreviousY - scrollY)).intValue(), scrollY > mPreviousY);
                    mPreviousY = scrollY;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    stepsBar.finishSlide();
                }
                return false;
            }
        });

    }

}
