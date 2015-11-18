package me.zhuao.android.sketch.activity;

import me.zhuao.android.sketch.activity.decorator.ActivityDecorator;
import me.zhuao.android.sketch.activity.decorator.RootDecorator;
import me.zhuao.android.sketch.activity.decorator.ToolbarDecorator;

public class ToolbarActivity extends BlankActivity {

    @Override
    protected ActivityDecorator getActivityDecorator() {
        return new ToolbarDecorator(new RootDecorator(this));
    }
}
