package me.zhuao.android.sample.activity;

import android.os.AsyncTask;
import android.os.Bundle;

import me.zhuao.android.sample.R;
import me.zhuao.android.sketch.ToolbarActivity;

public class MomentsActivity extends ToolbarActivity {

    private String jsmith = "jsmith";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateLayout(R.layout.moments);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProfileTask profileTask = new ProfileTask();
        profileTask.execute(jsmith);
    }

    private static class ProfileTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }
    }
}
