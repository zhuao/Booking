package com.tw.sketch.audiorecorder;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.widget.Toast;
import com.tw.sketch.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioRecorderService {

    private MediaRecorder mediaRecorder = null;
    private static SimpleDateFormat AUDIO_FILE_TIME_STAMP = new SimpleDateFormat("yyyyMMddHHmmssS");
    private Context context;

    public AudioRecorderService(Context context) {
        this.context = context;
    }

    public void startRecording() {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
//            mediaRecorder.setAudioChannels(2);
//            mediaRecorder.setAudioSamplingRate(2);
            mediaRecorder.setOutputFile(getTempAudioFile(context));
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            mediaRecorder.reset();
            mediaRecorder.release();
            throw new RuntimeException(e);
        }
    }

    public void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    public void release() {
        //TODO: To check whether recoreder is released.
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
        }
    }

    private String getTempAudioFile(Context context) throws IOException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/" + context.getResources().getString(R.string.app_name);
            File projectFolder = new File(path);
            if (!projectFolder.exists()) {
                projectFolder.mkdir();
            }
            return projectFolder.getAbsolutePath() + "/" + buildAudioFileName();
        }
        Toast.makeText(context, "SD card is not ready.", Toast.LENGTH_SHORT).show();
        throw new RuntimeException("External storage error");
    }

    private String buildAudioFileName() {
        return AUDIO_FILE_TIME_STAMP.format(new Date()) + ".3gp";
    }
}
