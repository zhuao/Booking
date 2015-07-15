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

    private float mPrevScrollY;

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
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mPrevScrollY = event.getY();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    stepsBar.slideText(scrollView.getScrollY());
                }
                return false;
            }
        });

    }

}
