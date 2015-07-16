package me.zhuao.android.sketch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import me.zhuao.android.sample.activity.EditTextActivity;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Test
    public void should_start_main_activity() {
        mainActivity = ActivityController.of(MainActivity.class).create().resume().get();

        mainActivity.text(null);

        String nextStartedActivityName = Robolectric.getShadowApplication().getNextStartedActivity().getComponent().getClassName();

        assertThat(nextStartedActivityName).isEqualTo(EditTextActivity.class.getName());
    }
}