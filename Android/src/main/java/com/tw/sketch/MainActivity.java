package com.tw.sketch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.tw.sketch.activity.AudioRecorderActivity;
import com.tw.sketch.activity.EditTextActivity;
import com.tw.sketch.activity.MomentsActivity;
import com.tw.sketch.activity.SpinnerActivity;

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
}
