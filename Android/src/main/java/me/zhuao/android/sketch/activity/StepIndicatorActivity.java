package me.zhuao.android.sketch.activity;

import android.app.Activity;
import android.os.Bundle;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.zhuao.android.sketch.R;
import me.zhuao.android.sketch.view.StepsBar;

public class StepIndicatorActivity extends Activity{

    @InjectView(R.id.step_indicator_bar)
    StepsBar stepsBar;

    @InjectView(R.id.step_indicator_scroll_view)
    ObservableScrollView scrollView;

    private float mPrevScrollY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_indicator_activity);
        ButterKnife.inject(this);

        addScrollListener();
    }

    private void addScrollListener() {
        scrollView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY, boolean b, boolean b1) {
                boolean scrollUp = mPrevScrollY < scrollY;
                if (scrollUp) {
                    stepsBar.slideText(scrollY);
                } else {
                    stepsBar.slideText(scrollY);
                }
                mPrevScrollY = scrollY;
            }

            @Override
            public void onDownMotionEvent() {

            }

            @Override
            public void onUpOrCancelMotionEvent(ScrollState scrollState) {

            }
        });

    }

}
