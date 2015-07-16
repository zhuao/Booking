package me.zhuao.android.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import me.zhuao.android.sample.R;
import me.zhuao.android.sketch.ToolbarActivity;


public class EditTextActivity extends ToolbarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateLayout(R.layout.edit_text);
    }

    public void clear(View view) {
        EditText editText = (EditText) findViewById(R.id.content_edit_text);
        editText.setText("");
    }
}
