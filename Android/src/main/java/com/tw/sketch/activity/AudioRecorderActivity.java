package com.tw.sketch.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.tw.sketch.R;
import com.tw.sketch.audiorecorder.AudioRecorderService;

public class AudioRecorderActivity extends Activity {



    private AudioRecorderService audioRecorderService = new AudioRecorderService(null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recorder);

        Button recorderBtn = ((Button) findViewById(R.id.record_btn));
        attachAudioRecorder(recorderBtn);

    }

    private void attachAudioRecorder(final Button audioRecordBtn) {
        audioRecordBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(AudioRecorderActivity.this, "started", Toast.LENGTH_SHORT).show();
                audioRecorderService.startRecording();
                return false;
            }
        });

        audioRecordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Toast.makeText(AudioRecorderActivity.this, "stopped", Toast.LENGTH_SHORT).show();
                    audioRecorderService.stopRecording();
                }
                return false;
            }
        });
    }

}
