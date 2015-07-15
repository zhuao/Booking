package me.zhuao.android.sketch.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import me.zhuao.android.sketch.BaseActivity;
import me.zhuao.android.sketch.R;


public class EditTextActivity extends BaseActivity {
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
