package com.tw.sketch.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.tw.sketch.R;
import com.tw.sketch.audiorecorder.*;

import java.io.File;

public class AudioRecorderActivity extends Activity {


    public static File audioFile = null;
    public static AudioRecorderService audioRecorderService = new AudioRecorderService();
    public static AudioPlayerService audioPlayerService = new AudioPlayerService();
    private AudioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recorder);

        RecorderButton recorderButton = (RecorderButton) findViewById(R.id.record_btn);
        recorderButton.appendClickAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.refreshData();
            }
        });

        ListView audioListView = (ListView) findViewById(R.id.audio_list_view);
        adapter = new AudioAdapter(this, null);
        audioListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        adapter.refreshData();
    }

    public void emptyFolder(View view) {
        AudioFolderOperator.emptyFolder(this);
        adapter.refreshData();
    }
}
