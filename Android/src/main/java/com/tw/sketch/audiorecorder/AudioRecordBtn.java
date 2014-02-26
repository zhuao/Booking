package com.tw.sketch.audiorecorder;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.tw.sketch.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioRecordBtn extends Button {

    private MediaRecorder mediaRecorder;
    private SimpleDateFormat AUDIO_FILE_TIME_STAMP = new SimpleDateFormat("yyyyMMddHHmmssS");


    public AudioRecordBtn(Context context) {
        super(context);
        attachAudioRecorder();
    }

    public AudioRecordBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        attachAudioRecorder();
    }

    public AudioRecordBtn(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        attachAudioRecorder();
    }

    private void attachAudioRecorder() {
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(), "started", Toast.LENGTH_SHORT).show();
                startRecording();
                return false;
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Toast.makeText(getContext(), "stopped", Toast.LENGTH_SHORT).show();
                    stopRecording();
                }
                return false;
            }
        });
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.reset();
        mediaRecorder.release();
    }

    private void startRecording() {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(getTempAudioFile());
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            mediaRecorder.reset();
            mediaRecorder.release();
            throw new RuntimeException(e);
        }
    }

    private String getTempAudioFile() throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/" + getContext().getResources().getString(R.string.app_name);
            File projectFolder = new File(path);
            if (!projectFolder.exists()) {
                projectFolder.mkdir();
            }
            return projectFolder.getAbsolutePath() + "/" + buildAudioFileName();
        }
        Toast.makeText(getContext(), "SD card is not ready.", Toast.LENGTH_SHORT).show();
        throw new RuntimeException("External storage error");
    }

    private String buildAudioFileName() {
        return AUDIO_FILE_TIME_STAMP.format(new Date()) + ".3gp";
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mediaRecorder.reset();
        mediaRecorder.release();
    }
}
