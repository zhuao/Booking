package me.zhuao.android.sketch.activity.decorator;

import me.zhuao.android.sketch.activity.BlankActivity;

public interface ActivityDecorator {
    void onCreate(int containerResId);

    void setContentView(int layoutResID);

    void onPostCreate();

    int getContentElementId();

    BlankActivity getActivity();
}
