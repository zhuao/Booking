package me.zhuao.android.sketch;

import android.support.v7.widget.Toolbar;
import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AppTestRunner.class)
public class ToolbarActivityTest {

    @Test
    public void should_have_a_tool_bar() {
        ToolbarActivity toolbarActivity = Robolectric.setupActivity(ToolbarActivity.class);

        View toolbar = toolbarActivity.findViewById(R.id.toolbar);

        assertThat(toolbar).isNotNull();
        assertThat(toolbar instanceof Toolbar).isTrue();
    }

}