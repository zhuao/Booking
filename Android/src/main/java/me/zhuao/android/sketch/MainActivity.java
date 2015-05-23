package me.zhuao.android.sketch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import me.zhuao.android.sketch.activity.AudioRecorderActivity;
import me.zhuao.android.sketch.activity.EditTextActivity;
import me.zhuao.android.sketch.activity.MomentsActivity;
import me.zhuao.android.sketch.activity.SpinnerActivity;
import me.zhuao.android.sketch.activity.StepIndicatorActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_lists);
    }

    public void spinner(View view) {
        startActivity(new Intent(this, SpinnerActivity.class));
    }

    public void text(View view) {
        startActivity(new Intent(this, EditTextActivity.class));
    }

    public void recorder(View view) {
        startActivity(new Intent(this, AudioRecorderActivity.class));
    }

    public void moments(View view) {
        startActivity(new Intent(this, MomentsActivity.class));
    }

    public void stepIndicator(View view) {
        startActivity(new Intent(this, StepIndicatorActivity.class));
    }
}
