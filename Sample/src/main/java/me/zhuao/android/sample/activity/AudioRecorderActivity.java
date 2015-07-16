package me.zhuao.android.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.File;

import me.zhuao.android.sample.R;
import me.zhuao.android.sketch.ToolbarActivity;
import me.zhuao.android.sample.audiorecorder.AudioAdapter;
import me.zhuao.android.sample.audiorecorder.AudioFolderOperator;
import me.zhuao.android.sample.audiorecorder.AudioPlayerService;
import me.zhuao.android.sample.audiorecorder.AudioRecorderService;
import me.zhuao.android.sample.audiorecorder.RecorderButton;

public class AudioRecorderActivity extends ToolbarActivity {


    public static File audioFile = null;
    public static AudioRecorderService audioRecorderService = new AudioRecorderService();
    public static AudioPlayerService audioPlayerService = new AudioPlayerService();
    private AudioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflateLayout(R.layout.recorder);

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
        super.onResume();
        adapter.refreshData();
    }

    public void emptyFolder(View view) {
        AudioFolderOperator.emptyFolder(this);
        adapter.refreshData();
    }
}
