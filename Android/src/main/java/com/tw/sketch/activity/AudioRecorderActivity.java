package com.tw.sketch.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tw.sketch.R;
import com.tw.sketch.audiorecorder.AudioPlayerService;
import com.tw.sketch.audiorecorder.AudioRecorderService;
import com.tw.sketch.audiorecorder.PlayerButton;

import java.io.File;

public class AudioRecorderActivity extends Activity {


    public static File audioFile = null;
    public static AudioRecorderService audioRecorderService = new AudioRecorderService();
    public static AudioPlayerService audioPlayerService = new AudioPlayerService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recorder);
    }


    public void emptyFolder(View view) {
        audioRecorderService.emptyFolder(this);
    }

}
