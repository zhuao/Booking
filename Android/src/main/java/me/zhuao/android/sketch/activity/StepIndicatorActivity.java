package me.zhuao.android.sketch.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.zhuao.android.sketch.R;
import me.zhuao.android.sketch.view.StepsBar;

public class StepIndicatorActivity extends Activity{

    @InjectView(R.id.step_indicator_bar)
    StepsBar stepsBar;

    @InjectView(R.id.step_indicator_scroll_view)
    ScrollView scrollView;

    private float mPrevScrollY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_indicator_activity);
        ButterKnife.inject(this);

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
