package com.thoughtworks.android.booking;

import android.content.Intent;
import android.widget.Button;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;


import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
@Ignore
public class MainActivityTest {

    private MainActivity mainActivity;

    @Test
    public void should_display_button_to_open_stepIndicator() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        Button stepIndicatorButton = (Button) mainActivity.findViewById(R.id.function_list_step_indicator);
        assertThat(stepIndicatorButton).containsText("Step Indicator");

//        stepIndicatorButton.performClick();

        Intent nextStartedActivity = ShadowApplication.getInstance().getNextStartedActivity();
//        assertThat(nextStartedActivity).hasComponent(RuntimeEnvironment.application.getPackageName(), StepIndicatorActivity.class);
    }

}